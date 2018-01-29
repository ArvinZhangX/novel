package com.ifengxue.novel.spider.enums;

import java.io.Serializable;

/**
 * 已经被支持的小说站点枚举
 * @author LiuKeFeng
 * @date   2016年9月27日
 */
public enum NovelSiteEnum implements Serializable {
	BiQuGe(1, "biquge.tw"),
	BiXiaWenXue(2, "bxwx8.org"),
	DingDianXiaoShuo(3, "23wx.com");
	private int id;
	private String comment;
	private NovelSiteEnum(int id, String comment) {
		this.id = id;
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public static NovelSiteEnum getEnumByComment(String comment) {
		if (comment == null) throw new IllegalArgumentException("comment 不能为null");
		for (NovelSiteEnum novelSiteEnum : values()) {
			if (comment.equalsIgnoreCase(novelSiteEnum.getComment())) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("不支持的网站：" + comment);
	}
	/**
	 * @param url 可以是一个完整的url，也可以是只包含域名的url
	 * @return
	 */
	public static NovelSiteEnum getEnumByUrl(String url) {
		if (url == null) throw new IllegalArgumentException("url 不能为null");
		for (NovelSiteEnum novelSiteEnum : values()) {
			if (url.contains(novelSiteEnum.getComment())) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("不支持的网站：" + url);
	}
	public static NovelSiteEnum getEnumById(int id) {
		for (NovelSiteEnum novelSiteEnum : values()) {
			if (novelSiteEnum.getId() == id ) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("不支持的网站id：" + id);
	}
}
