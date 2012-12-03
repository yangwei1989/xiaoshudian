package com.xiaoshudian.update.net;
/**
 * 抓取数据domain
 * @author zhangya
 */
public class SArticle {
	
	private int id;
	private String title;
	private String content;
	private String author;
	private String time;
	private String simple_url;
	private String detail_url;
	private String siteName;
	private String siteIndexUrl;
	private String postTime;
	private String fetchTime;
	private String tag;
	private int flag;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSimple_url() {
		return simple_url;
	}
	public void setSimple_url(String simple_url) {
		this.simple_url = simple_url;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteIndexUrl() {
		return siteIndexUrl;
	}
	public void setSiteIndexUrl(String siteIndexUrl) {
		this.siteIndexUrl = siteIndexUrl;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getFetchTime() {
		return fetchTime;
	}
	public void setFetchTime(String fetchTime) {
		this.fetchTime = fetchTime;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "SArticle [id=" + id + ", title=" + title + ", content="
				+ content + ", author=" + author + ", time=" + time
				+ ", simple_url=" + simple_url + ", detail_url=" + detail_url
				+ ", siteName=" + siteName + ", siteIndexUrl=" + siteIndexUrl
				+ ", postTime=" + postTime + ", fetchTime=" + fetchTime
				+ ", tag=" + tag + ", flag=" + flag + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
