package com.ifengxue.novel.spider.entity;

/**
 * 笔下文学小说实体
 * @author LiuKeFeng
 * @date   2016年10月2日
 */
public class BxwxBook extends Book {
	private static final long serialVersionUID = -3029967694539036578L;
	private String lastUpdateChapter;
	private String lastUpdateChapterUrl;
	private String author;
	private String size;
	private String lastUpdateTime;
	private String status;
	public String getLastUpdateChapter() {
		return lastUpdateChapter;
	}

	public void setLastUpdateChapter(String lastUpdateChapter) {
		this.lastUpdateChapter = lastUpdateChapter;
	}

	public String getLastUpdateChapterUrl() {
		return lastUpdateChapterUrl;
	}

	public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
		this.lastUpdateChapterUrl = lastUpdateChapterUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BxwxBook [bookname=" + bookname + ", url=" + url + ", author=" + author + ", size=" + size
				+ ", lastUpdateChapter=" + lastUpdateChapter + ", lastUpdateChapterUrl=" + lastUpdateChapterUrl
				+ ", lastUpdateTime=" + lastUpdateTime + ", status=" + status + "]";
	}
}
