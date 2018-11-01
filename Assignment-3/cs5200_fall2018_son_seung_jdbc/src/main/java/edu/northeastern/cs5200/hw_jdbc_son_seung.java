package edu.northeastern.cs5200;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.Connection;

public class hw_jdbc_son_seung {
	///////////////////////////////////////////////////////////////////
	// Stored Procedures are also in here. Not called in main method.//
	///////////////////////////////////////////////////////////////////
	private static java.sql.Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement pStatement = null;
	private static ResultSet results = null;
	
	public static void main(String[] args) {
		DeveloperDao.getInstance().truncateAll();

		// Inserts
		System.out.println("Working on Inserts. This will take a while...");
		questionOne();
		questionTwo();
		questionThree();
		questionFour();

		// Updates
		System.out.println("Working on Updates.");
		// Question 1
		DeveloperDao devDao = DeveloperDao.getInstance();
		Developer charlie = devDao.findDeveloperById(34);

		Phone phone = new Phone("333-444-5555", true, charlie);
		List<Phone> cPhones = new ArrayList<Phone>();
		cPhones.add(phone);
		charlie.setPhones(cPhones);
		devDao.updateDeveloper(34, charlie);

		// Question 2
		WidgetDao widgetDao = WidgetDao.getInstance();
		Widget head = widgetDao.findWidgetById(3);
		Widget intro = widgetDao.findWidgetById(4);
		Widget image = widgetDao.findWidgetById(5);
		head.setOrder(3);
		intro.setOrder(1);
		image.setOrder(2);
		widgetDao.updateWidget(3, head);
		widgetDao.updateWidget(4, intro);
		widgetDao.updateWidget(5, image);

		// Question 3
		PageDao pageDao = PageDao.getInstance();

		Collection<Page> pages = pageDao.findPagesForWebsite(567);

		for (Page page : pages) {
			String title = page.getTitle();
			page.setTitle("CNET - " + title);
			pageDao.updatePage(page.getId(), page);
		}

		// Question 4
		RoleDao roleDao = RoleDao.getInstance();
		roleDao.updatePageRole(23, 123, 2);
		roleDao.updatePageRole(34, 123, 4);

		// Deletes
		// Question 1
		System.out.println("Working on Deletes...");
		System.out.println("There are no addresses inserted by this code. To delete Alice's address, add her address.");
		DeveloperDao developerDao = DeveloperDao.getInstance();
		developerDao.deleteAddressByDeveloperId(12);

		// Question 2
		Collection<Widget> w = widgetDao.findAllWidgets();
		List<Widget> widgets = (List<Widget>) w;
		Widget lastWidget = null;

		for (Widget widget : widgets) {
			if (widget.getPageId() == 345) {
				if (lastWidget == null) {
					lastWidget = widget;
				}
				else if (widget.getOrder() > lastWidget.getOrder()) {
					lastWidget = widget;
				}
			}
		}
		widgetDao.deleteWidget(lastWidget.getId());

		// Question 3
		pageDao = PageDao.getInstance();
		Collection<Page> p = pageDao.findAllPages();
		List<Page> myPages = (List<Page>) p;
		Page lastPage = null;

		for (Page page : myPages) {
			if (page.getWebsiteId() == 345) {
				if (lastPage == null) {
					lastPage = page;
				}
				else if (page.getUpdated().getTime() > lastPage.getUpdated().getTime()) {
					lastPage = page;
				}
			}
		}
		pageDao.deletePage(lastPage.getId());
		
		// Question 4
		// Cascaded on MySQL side
		WebsiteDao websiteDao = WebsiteDao.getInstance();
		websiteDao.deleteWebsite(567);
		
		System.out.println("Done! Stored Procedures are methods in this class.");
	}

	// Stored Procedures
	public static void getUnansweredQuestions() throws SQLException {
		connection = Connection.getInstance().getConnection();
		
		final String QUERY = "SELECT question.text, COUNT(answer.id) AS AnswerCount FROM question "
				+ "JOIN user ON user.id = question.user_id JOIN answer ON answer.user_id = user.id "
				+ "WHERE answer.correctAnswer = false GROUP BY question.module"
				+ "HAVING MAX(AnswerCount)";
		
		statement = connection.createStatement();
		results = statement.executeQuery(QUERY);
		
		while (results.next()) {
			String text = results.getString("question.text");
			int count = results.getInt("AnswerCount");
			
			System.out.println(text + " " + count);
		}
		
		if (connection != null)
			connection.close();
	}
	public static void endorsedUsersForWeek(Date startWeek, Date endWeek) throws SQLException{
		connection = Connection.getInstance().getConnection();
		
		final String QUERY = "SELECT user.id, full_name, user.approvedUser FROM "
				+ "(SELECT user.id, CONCAT(user.first_name, \" \", user.last_name) AS full_name "
				+ "FROM question JOIN user on question.user_id = user.id JOIN answer ON user.id = answer.user_id "
				+ "WHERE ? < posted_on AND posted_on < ? ORDER BY COUNT(endorsed_by_instructor) DESC LIMIT 5) "
				+ "ORDER BY user.first_name";
		
		pStatement = connection.prepareStatement(QUERY);
		pStatement.setDate(1, startWeek);
		pStatement.setDate(2, endWeek);
		results = statement.executeQuery(QUERY);
		
		while (results.next()) {
			String id = results.getString("user.id");
			String name = results.getString("full_name");
			Boolean approved = results.getBoolean("user.approvedUser");
			
			System.out.println(id + " " + name + " " + approved);
		}
		
		if (connection != null)
			connection.close();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Inserts: Question 1
	public static void questionOne() {
		DeveloperDao developerDao = DeveloperDao.getInstance();
		UserDao userDao = UserDao.getInstance();

		Developer alice = new Developer("4321rewq", 12, "alice", "alice", "Alice", "Wonder", "alice@wonder.com", null);
		developerDao.createDeveloper(alice);
		Developer bob = new Developer("5432trew", 23, "bob", "bob", "Bob", "Marley", "bob@marley.com", null);
		developerDao.createDeveloper(bob);
		Developer charlie = new Developer("6543ytre", 34, "charlie", "charlie", "Charlie", "Garcia", "chuch@garcia.com",
				null);
		developerDao.createDeveloper(charlie);

		User dan = new User(45, "Dan", "Martin", "dan", "dan", "dan@martin.com", null);
		userDao.createUser(dan);
		User ed = new User(56, "Ed", "Karaz", "ed", "ed", "ed@kar.com", null);
		userDao.createUser(ed);
	}

	// Question 2
	public static void questionTwo() {
		WebsiteDao websiteDao = WebsiteDao.getInstance();
		RoleDao roleDao = RoleDao.getInstance();
		PriviledgeDao privilegeDao = PriviledgeDao.getInstance();

		Website facebook = new Website(123, "Facebook", "an online social media and social networking service",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1234234);
		websiteDao.createWebsiteForDeveloper(12, facebook);
		roleDao.assignWebsiteRole(12, 123, 0);
		privilegeDao.assignWebsitePriviledge(12, 123, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(12, 123, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(12, 123, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(12, 123, Priviledge.DELETE.toString());
		roleDao.assignWebsiteRole(23, 123, 3);
		privilegeDao.assignWebsitePriviledge(23, 123, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(23, 123, Priviledge.UPDATE.toString());
		roleDao.assignWebsiteRole(34, 123, 1);
		privilegeDao.assignWebsitePriviledge(34, 123, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(34, 123, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(34, 123, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(34, 123, Priviledge.DELETE.toString());

		Website twitter = new Website(234, "Twitter", "an online news and social networking service",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 4321543);
		websiteDao.createWebsiteForDeveloper(23, twitter);
		roleDao.assignWebsiteRole(23, 234, 0);
		privilegeDao.assignWebsitePriviledge(23, 234, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(23, 234, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(23, 234, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(23, 234, Priviledge.DELETE.toString());
		roleDao.assignWebsiteRole(34, 234, 3);
		privilegeDao.assignWebsitePriviledge(34, 234, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(34, 234, Priviledge.UPDATE.toString());
		roleDao.assignWebsiteRole(12, 234, 1);
		privilegeDao.assignWebsitePriviledge(12, 234, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(12, 234, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(12, 234, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(12, 234, Priviledge.DELETE.toString());

		Website wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 3456654);
		websiteDao.createWebsiteForDeveloper(34, wikipedia);
		roleDao.assignWebsiteRole(34, 345, 0);
		privilegeDao.assignWebsitePriviledge(34, 345, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(34, 345, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(34, 345, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(34, 345, Priviledge.DELETE.toString());
		roleDao.assignWebsiteRole(12, 345, 3);
		privilegeDao.assignWebsitePriviledge(12, 345, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(12, 345, Priviledge.UPDATE.toString());
		roleDao.assignWebsiteRole(23, 345, 1);
		privilegeDao.assignWebsitePriviledge(23, 345, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(23, 345, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(23, 345, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(23, 345, Priviledge.DELETE.toString());

		Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channe",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 6543345);
		websiteDao.createWebsiteForDeveloper(12, cnn);
		roleDao.assignWebsiteRole(12, 456, 0);
		privilegeDao.assignWebsitePriviledge(12, 456, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(12, 456, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(12, 456, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(12, 456, Priviledge.DELETE.toString());
		roleDao.assignWebsiteRole(23, 456, 3);
		privilegeDao.assignWebsitePriviledge(23, 456, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(23, 456, Priviledge.UPDATE.toString());
		roleDao.assignWebsiteRole(34, 456, 1);
		privilegeDao.assignWebsitePriviledge(34, 456, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(34, 456, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(34, 456, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(34, 456, Priviledge.DELETE.toString());

		Website cnet = new Website(567, "CNET",
				"an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 5433455);
		websiteDao.createWebsiteForDeveloper(23, cnet);
		roleDao.assignWebsiteRole(23, 567, 0);
		privilegeDao.assignWebsitePriviledge(23, 567, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(23, 567, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(23, 567, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(23, 567, Priviledge.DELETE.toString());
		roleDao.assignWebsiteRole(34, 567, 3);
		privilegeDao.assignWebsitePriviledge(34, 567, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(34, 567, Priviledge.UPDATE.toString());
		roleDao.assignWebsiteRole(12, 567, 1);
		privilegeDao.assignWebsitePriviledge(12, 567, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(12, 567, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(12, 567, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(12, 567, Priviledge.DELETE.toString());

		Website gizmodo = new Website(678, "Gizmodo",
				"a design, technology, science and science fiction website that also writes articles on politics",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 4322345);
		websiteDao.createWebsiteForDeveloper(34, gizmodo);
		roleDao.assignWebsiteRole(34, 678, 0);
		privilegeDao.assignWebsitePriviledge(34, 678, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(34, 678, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(34, 678, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(34, 678, Priviledge.DELETE.toString());
		roleDao.assignWebsiteRole(12, 123, 3);
		privilegeDao.assignWebsitePriviledge(12, 678, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(12, 678, Priviledge.UPDATE.toString());
		roleDao.assignWebsiteRole(23, 123, 1);
		privilegeDao.assignWebsitePriviledge(23, 678, Priviledge.CREATE.toString());
		privilegeDao.assignWebsitePriviledge(23, 678, Priviledge.READ.toString());
		privilegeDao.assignWebsitePriviledge(23, 678, Priviledge.UPDATE.toString());
		privilegeDao.assignWebsitePriviledge(23, 678, Priviledge.DELETE.toString());
	}

	// Question 3
	public static void questionThree() {
		PageDao pageDao = PageDao.getInstance();
		RoleDao roleDao = RoleDao.getInstance();
		PriviledgeDao privilegeDao = PriviledgeDao.getInstance();

		Page home = new Page(123, "Home", "Landing page", new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()), 123434);
		pageDao.createPageForWebsite(567, home);
		roleDao.assignPageRole(12, 123, 3);
		privilegeDao.assignPagePriviledge(12, 123, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(12, 123, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(23, 123, 4);
		privilegeDao.assignPagePriviledge(23, 123, Priviledge.READ.toString());
		roleDao.assignPageRole(34, 123, 2);
		privilegeDao.assignPagePriviledge(34, 123, Priviledge.CREATE.toString());
		privilegeDao.assignPagePriviledge(34, 123, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(34, 123, Priviledge.UPDATE.toString());

		Page about = new Page(234, "About", "Website description", new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()), 234545);
		pageDao.createPageForWebsite(678, about);
		roleDao.assignPageRole(23, 234, 3);
		privilegeDao.assignPagePriviledge(23, 234, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(23, 234, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(34, 234, 4);
		privilegeDao.assignPagePriviledge(34, 234, Priviledge.READ.toString());
		roleDao.assignPageRole(12, 234, 2);
		privilegeDao.assignPagePriviledge(12, 234, Priviledge.CREATE.toString());
		privilegeDao.assignPagePriviledge(12, 234, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(12, 234, Priviledge.UPDATE.toString());

		Page contact = new Page(345, "Contact", "Addresses, phones, and contact info",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 345656);
		pageDao.createPageForWebsite(345, contact);
		roleDao.assignPageRole(34, 345, 3);
		privilegeDao.assignPagePriviledge(34, 345, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(34, 345, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(12, 345, 4);
		privilegeDao.assignPagePriviledge(12, 345, Priviledge.READ.toString());
		roleDao.assignPageRole(23, 345, 2);
		privilegeDao.assignPagePriviledge(23, 345, Priviledge.CREATE.toString());
		privilegeDao.assignPagePriviledge(23, 345, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(23, 345, Priviledge.UPDATE.toString());

		Page preferences = new Page(456, "Preferences", "Where users can configure their preferences",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 456776);
		pageDao.createPageForWebsite(456, preferences);
		roleDao.assignPageRole(12, 456, 3);
		privilegeDao.assignPagePriviledge(12, 456, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(12, 456, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(23, 456, 4);
		privilegeDao.assignPagePriviledge(23, 456, Priviledge.READ.toString());
		roleDao.assignPageRole(34, 456, 2);
		privilegeDao.assignPagePriviledge(34, 456, Priviledge.CREATE.toString());
		privilegeDao.assignPagePriviledge(34, 456, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(34, 456, Priviledge.UPDATE.toString());

		Page profile = new Page(567, "Profile", "Users can configure their personal information",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 567878);
		pageDao.createPageForWebsite(567, profile);
		roleDao.assignPageRole(23, 567, 3);
		privilegeDao.assignPagePriviledge(23, 567, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(23, 567, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(34, 567, 4);
		privilegeDao.assignPagePriviledge(34, 567, Priviledge.READ.toString());
		roleDao.assignPageRole(12, 567, 2);
		privilegeDao.assignPagePriviledge(12, 567, Priviledge.CREATE.toString());
		privilegeDao.assignPagePriviledge(12, 567, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(12, 567, Priviledge.UPDATE.toString());
	}

	// Question 4
	public static void questionFour() {
		WidgetDao widgetDao = WidgetDao.getInstance();

		HeadingWidget head123 = new HeadingWidget(1, "head123", 0, 0, null, null, "Welcome", 0);
		widgetDao.createWidgetForPage(123, head123);
		HtmlWidget post234 = new HtmlWidget(2, "post234", 0, 0, null, null, "<p>Lorem</p>", 0);
		widgetDao.createWidgetForPage(234, post234);
		HeadingWidget head345 = new HeadingWidget(3, "head345", 0, 0, null, null, "Hi", 1);
		widgetDao.createWidgetForPage(345, head345);
		HtmlWidget intro456 = new HtmlWidget(4, "intro456", 0, 0, null, null, "<h1>Hi</h1>", 2);
		widgetDao.createWidgetForPage(345, intro456);
		ImageWidget image345 = new ImageWidget(5, "image345", 50, 100, null, null, null, 3);
		widgetDao.createWidgetForPage(345, image345);
		YouTubeWidget video456 = new YouTubeWidget(6, "video456", 400, 300, null, null, null, 0);
		widgetDao.createWidgetForPage(456, video456);
	}
}
