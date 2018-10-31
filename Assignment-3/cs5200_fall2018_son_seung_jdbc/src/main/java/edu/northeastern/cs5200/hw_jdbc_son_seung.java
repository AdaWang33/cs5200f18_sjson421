package edu.northeastern.cs5200;

import java.sql.Date;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;

public class hw_jdbc_son_seung {
	public static void main(String[] args) {
		DeveloperDao.getInstance().truncateAll();

		System.out.println("Working on Question 1...");
		questionOne();
		System.out.println("Working on Question 2. Might take a while...");
		questionTwo();
		System.out.println("Working on Question 3. Might take a while...");
		questionThree();
		System.out.println("Working on Question 4...");
		questionFour();
		System.out.println("Done!");
	}

	// Question 1
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
		roleDao.assignPageRole(23, 123, 3);
		privilegeDao.assignPagePriviledge(23, 234, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(23, 234, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(34, 123, 4);
		privilegeDao.assignPagePriviledge(34, 234, Priviledge.READ.toString());
		roleDao.assignPageRole(12, 123, 2);
		privilegeDao.assignPagePriviledge(12, 234, Priviledge.CREATE.toString());
		privilegeDao.assignPagePriviledge(12, 234, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(12, 234, Priviledge.UPDATE.toString());

		Page contact = new Page(345, "Contact", "Addresses, phones, and contact info",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 345656);
		pageDao.createPageForWebsite(345, contact);
		roleDao.assignPageRole(34, 123, 3);
		privilegeDao.assignPagePriviledge(34, 345, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(34, 345, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(12, 123, 4);
		privilegeDao.assignPagePriviledge(12, 345, Priviledge.READ.toString());
		roleDao.assignPageRole(23, 123, 2);
		privilegeDao.assignPagePriviledge(23, 345, Priviledge.CREATE.toString());
		privilegeDao.assignPagePriviledge(23, 345, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(23, 345, Priviledge.UPDATE.toString());

		Page preferences = new Page(456, "Preferences", "Where users can configure their preferences",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 456776);
		pageDao.createPageForWebsite(456, preferences);
		roleDao.assignPageRole(12, 123, 3);
		privilegeDao.assignPagePriviledge(12, 456, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(12, 456, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(23, 123, 4);
		privilegeDao.assignPagePriviledge(23, 456, Priviledge.READ.toString());
		roleDao.assignPageRole(34, 123, 2);
		privilegeDao.assignPagePriviledge(34, 456, Priviledge.CREATE.toString());
		privilegeDao.assignPagePriviledge(34, 456, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(34, 456, Priviledge.UPDATE.toString());

		Page profile = new Page(567, "Profile", "Users can configure their personal information",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 567878);
		pageDao.createPageForWebsite(567, profile);
		roleDao.assignPageRole(23, 123, 3);
		privilegeDao.assignPagePriviledge(23, 567, Priviledge.READ.toString());
		privilegeDao.assignPagePriviledge(23, 567, Priviledge.UPDATE.toString());
		roleDao.assignPageRole(34, 123, 4);
		privilegeDao.assignPagePriviledge(34, 567, Priviledge.READ.toString());
		roleDao.assignPageRole(12, 123, 2);
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
