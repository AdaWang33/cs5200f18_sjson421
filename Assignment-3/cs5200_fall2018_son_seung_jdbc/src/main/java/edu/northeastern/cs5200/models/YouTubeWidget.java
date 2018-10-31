package edu.northeastern.cs5200.models;

public class YouTubeWidget extends Widget {
	private String url;
	private Boolean shareble = false;
	private Boolean expandable = false;
	
	public YouTubeWidget(int id, String name, int width, int height, String cssStyle, String cssClass, String text,
			int order) {
		super(id, name, width, height, cssStyle, cssClass, text, order);
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getShareble() {
		return shareble;
	}
	public void setShareble(Boolean shareble) {
		this.shareble = shareble;
	}
	public Boolean getExpandable() {
		return expandable;
	}
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}
	
}
