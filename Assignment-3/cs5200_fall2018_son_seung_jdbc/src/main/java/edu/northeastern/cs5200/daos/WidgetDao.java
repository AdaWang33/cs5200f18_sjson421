package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.*;

public class WidgetDao implements WidgetImpl {

	private static WidgetDao instance = null;
	private java.sql.Connection connection = null;
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private ResultSet results = null;

	private static final String CREATE_WIDGET = "INSERT INTO widget VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String FIND_ALL_WIDGETS = "SELECT * FROM widget";
	private static final String FIND_WIDGET_BY_ID = "SELECT * FROM widget WHERE id = ?";
	private static final String FIND_WIDGETS_FOR_PAGE = "SELECT * FROM widget JOIN page"
			+ "ON widget.page_id = page.id WHERE page.id = ?";
	private static final String UPDATE_WIDGET = "UPDATE widget SET name=?, width=?,height=?,css_class=?"
			+ "css_style=?,text=?,order=?,url=?,shareable=?,expandable=?,src=?,size=?,html=?,page_id=?"
			+ "WHERE id = ?";
	private static final String DELETE_WIDGET = "DELETE FROM widget WHERE id = ?";

	private WidgetDao() {
	}

	public static WidgetDao getInstance() {
		instance = new WidgetDao();
		return instance;
	}

	@Override
	public void createWidgetForPage(int pageId, Widget widget) {
		connection = Connection.getInstance().getConnection();
		try {
			int id = widget.getId();
			String name = widget.getName();
			int width = widget.getWidth();
			int height = widget.getHeight();
			String css_class = widget.getCssClass();
			String css_style = widget.getCssStyle();
			String text = widget.getText();
			int order = widget.getOrder();
			
			String url = null;
			Boolean shareble = false;
			Boolean expandable = false;
			String src = null;
			int size = 0;
			String html = null;
			
			if (widget instanceof YouTubeWidget) {
				url = ((YouTubeWidget) widget).getUrl();
				shareble = ((YouTubeWidget) widget).getShareble();
				expandable = ((YouTubeWidget) widget).getExpandable();
			}
			else if (widget instanceof ImageWidget) {
				src = ((ImageWidget) widget).getSrc();
			}
			else if (widget instanceof HeadingWidget) {
				size = ((HeadingWidget) widget).getSize();
			}
			else if (widget instanceof HtmlWidget) {
				html = ((HtmlWidget) widget).getHtml();
			}

			pStatement = connection.prepareStatement(CREATE_WIDGET);

			pStatement.setInt(1, id);
			pStatement.setString(2, name);
			pStatement.setInt(3, width);
			pStatement.setInt(4, height);
			pStatement.setString(5, css_class);
			pStatement.setString(6, css_style);
			pStatement.setString(7, text);
			pStatement.setInt(8, order);
			pStatement.setString(9, url);
			pStatement.setBoolean(10, shareble);
			pStatement.setBoolean(11, expandable);
			pStatement.setString(12, src);
			pStatement.setInt(13, size);
			pStatement.setString(14, html);
			pStatement.setInt(15, pageId);
			
			pStatement.executeUpdate();
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Collection<Widget> findAllWidgets() {
		connection = Connection.getInstance().getConnection();
		Collection<Widget> widgets = new ArrayList<Widget>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_WIDGETS);
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String css_class = results.getString("css_class");
				String css_style = results.getString("css_style");
				String text = results.getString("text");
				int order = results.getInt("order");

				Widget w = new Widget(id, name, width, height, css_class, css_style, text, order);
				widgets.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return widgets;
	}

	@Override
	public Widget findWidgetById(int widgetId) {
		connection = Connection.getInstance().getConnection();
		Widget w = null;
		try {
			pStatement = connection.prepareStatement(FIND_WIDGET_BY_ID);
			pStatement.setInt(1, widgetId);
			pStatement.executeQuery();
			
			if (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String css_class = results.getString("css_class");
				String css_style = results.getString("css_style");
				String text = results.getString("text");
				int order = results.getInt("order");

				w = new Widget(id, name, width, height, css_class, css_style, text, order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return w;
	}

	@Override
	public Collection<Widget> findWidgetsForPage(int pageId) {
		connection = Connection.getInstance().getConnection();
		Collection<Widget> widgets = new ArrayList<Widget>();
		try {
			pStatement = connection.prepareStatement(FIND_WIDGETS_FOR_PAGE);
			pStatement.setInt(1, pageId);
			pStatement.executeQuery();
			
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String css_class = results.getString("css_class");
				String css_style = results.getString("css_style");
				String text = results.getString("text");
				int order = results.getInt("order");
				
				Widget w = new Widget(id, name, width, height, css_class, css_style, text, order);
				widgets.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return widgets;
	}

	@Override
	public int updateWidget(int widgetId, Widget widget) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		
		try {
			int id = widget.getId();
			String name = widget.getName();
			int width = widget.getWidth();
			int height = widget.getHeight();
			String css_class = widget.getCssClass();
			String css_style = widget.getCssStyle();
			String text = widget.getText();
			int order = widget.getOrder();
			
			String url = null;
			Boolean shareble = null;
			Boolean expandable = null;
			String src = null;
			int size = 0;
			String html = null;
			
			if (widget instanceof YouTubeWidget) {
				url = ((YouTubeWidget) widget).getUrl();
				shareble = ((YouTubeWidget) widget).getShareble();
				expandable = ((YouTubeWidget) widget).getExpandable();
			}
			else if (widget instanceof ImageWidget) {
				src = ((ImageWidget) widget).getSrc();
			}
			else if (widget instanceof HeadingWidget) {
				size = ((HeadingWidget) widget).getSize();
			}
			else if (widget instanceof HtmlWidget) {
				html = ((HtmlWidget) widget).getHtml();
			}
			
			pStatement = connection.prepareStatement(UPDATE_WIDGET);
			pStatement.setInt(1, id);
			pStatement.setString(2, name);
			pStatement.setInt(3, width);
			pStatement.setInt(4, height);
			pStatement.setString(5, css_class);
			pStatement.setString(6, css_style);
			pStatement.setString(7, text);
			pStatement.setInt(8, order);
			pStatement.setString(9, url);
			pStatement.setBoolean(10, shareble);
			pStatement.setBoolean(11, expandable);
			pStatement.setString(12, src);
			pStatement.setInt(13, size);
			pStatement.setString(14, html);
			pStatement.setInt(15, widget.getPage().getId());

			pStatement.executeUpdate();
			success = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	//Cascade on MySQL
	@Override
	public int deleteWidget(int widgetId) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(DELETE_WIDGET);
			pStatement.setInt(1, widgetId);

			pStatement.executeUpdate();
			success = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

}
