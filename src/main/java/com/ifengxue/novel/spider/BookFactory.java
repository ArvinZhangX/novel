package com.ifengxue.novel.spider;

import com.ifengxue.novel.spider.entity.BxwxBook;
import com.ifengxue.novel.spider.impl.book.BxwxBookSpider;
import com.ifengxue.novel.spider.interfaces.INovelBookSpider;

/**
 * 书列表爬虫工厂
 * @author LiuKeFeng
 * @date   2016年10月2日
 */
public final class BookFactory {
	private BookFactory() {}
	/**
	 * 获取一个笔下文学站点的小说爬虫
	 * @return
	 */
	public static INovelBookSpider<BxwxBook> getBxwxBookSpider() {
		return new BxwxBookSpider();
	}
}
