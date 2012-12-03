package com.xiaoshudian.data.domain;


public class ITArticle extends POJO {

	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private String content;
	private String detailurl;
	private String sitename;
	private String posttime;
	private String fetchtime;
	private String tag;
	private int flag;

	@Override
	protected String getTableName() {
		return "news_it";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}

	public String getFetchtime() {
		return fetchtime;
	}

	public void setFetchtime(String fetchtime) {
		this.fetchtime = fetchtime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ITArticle [title=" + title + ", author=" + author
				+ ", content=" + content + ", detailurl=" + detailurl
				+ ", sitename=" + sitename + ", posttime=" + posttime
				+ ", fetchtime=" + fetchtime + ", tag=" + tag + ", flag="
				+ flag + "]";
	}

}
