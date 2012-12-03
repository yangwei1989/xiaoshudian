package com.xiaoshudian.data.domain;

/**
 * article status like : click num and so on
 */
public class ArticleStatus extends POJO {

	private static final long serialVersionUID = 4520077631570762099L;

	private int news_id;
	// 点击量
	private int click;
	// 图片不显示
	private int image_error;
	// 侵权
	private int ower_error;
	// 排版错误
	private int format_error;
	// 文章错误
	private int article_error;
	private int flag;

	@Override
	protected String getTableName() {
		return "news_it_status";
	}

	public int getNews_id() {
		return news_id;
	}

	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public int getImage_error() {
		return image_error;
	}

	public void setImage_error(int image_error) {
		this.image_error = image_error;
	}

	public int getOwer_error() {
		return ower_error;
	}

	public void setOwer_error(int ower_error) {
		this.ower_error = ower_error;
	}

	public int getFormat_error() {
		return format_error;
	}

	public void setFormat_error(int format_error) {
		this.format_error = format_error;
	}

	public int getArticle_error() {
		return article_error;
	}

	public void setArticle_error(int article_error) {
		this.article_error = article_error;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ArticleStatus [news_id=" + news_id + ", click="
				+ click + ", image_error=" + image_error + ", ower_error="
				+ ower_error + ", format_error=" + format_error
				+ ", article_error=" + article_error + ", flag=" + flag + "]";
	}

	public static void main(String[] args) {
		
		ArticleStatus status = new ArticleStatus();
		System.out.println(status.insert());
		
	}
	
}
