package com.ifengxue.novel.spider.impl.content;

import com.ifengxue.novel.spider.exceptions.NovelSpiderException;

/**
 * 通用的小说章节内容爬取类
 * @author LiuKeFeng
 * @date   2016年9月18日
 */
public class DefaultNovelContentSpider extends AbstractNovelContentSpider {
	public DefaultNovelContentSpider(String comment) {
		this.domain = allRuleMap.get(comment).get("url").getText();
		this.simpleDomain = domain.substring(domain.indexOf(".") + 1);
	}
	
	public static void main(String[] args) throws NovelSpiderException {
		DefaultNovelContentSpider erSanSpider = new DefaultNovelContentSpider("23wx.com");
		System.out.println(erSanSpider.getContent("html/42/42377/18775689.html"));
		
		DefaultNovelContentSpider biQuGeSpider = new DefaultNovelContentSpider("biquge.tw");
		System.out.println(biQuGeSpider.getContent("0_5/909854.html"));
		
		DefaultNovelContentSpider bxwxSpider = new DefaultNovelContentSpider("bxwx8.org");
		System.out.println(bxwxSpider.getContent("b/70/70093/11969964.html"));
	}
}
