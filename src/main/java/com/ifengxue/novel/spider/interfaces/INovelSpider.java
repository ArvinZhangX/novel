package com.ifengxue.novel.spider.interfaces;

import com.ifengxue.novel.spider.exceptions.NovelSpiderException;

/**
 * @author LiuKeFeng
 * @date   2016年9月17日
 */
public interface INovelSpider {
	/**
	 * 爬取页面并返回爬取结果，爬取失败抛出异常
	 * @param url 要爬取的url（不包括域名）
	 * @param charset 网页编码格式
	 */
	public String crawl(String url, String charset) throws NovelSpiderException;
	/**
	 * 验证抓取到的数据是否符合要求
	 * @param t
	 */
	public boolean verify(Object obj);
	/**
	 * 设置要爬取的域名
	 * @param domain
	 */
	public void setDomain(String domain);
	/**
	 * @return 获取爬取的域名
	 */
	public String getDomain();
}
