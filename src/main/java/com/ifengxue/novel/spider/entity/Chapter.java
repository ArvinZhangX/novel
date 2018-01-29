package com.ifengxue.novel.spider.entity;

import java.io.Serializable;

/**
 * 章节实体
 * @author LiuKeFeng
 * @date   2016年9月17日
 */
public class Chapter implements Serializable, Cloneable {
	private static final long serialVersionUID = 7689002555266466585L;
	/** 小说链接，和域名拼接后才可用 */
	private String href;
	/** 章节名称 */
	private String text;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public Chapter clone() throws CloneNotSupportedException {
		return (Chapter) super.clone();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chapter other = (Chapter) obj;
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChapterVO [href=" + href + ", text=" + text + "]";
	}
}
