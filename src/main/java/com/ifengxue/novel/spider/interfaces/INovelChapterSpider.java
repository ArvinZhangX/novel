package com.ifengxue.novel.spider.interfaces;

import java.util.List;

import com.ifengxue.novel.spider.entity.Chapter;
import com.ifengxue.novel.spider.exceptions.NovelSpiderException;

/**
 * @author LiuKeFeng
 * @date   2016年9月17日
 */
public interface INovelChapterSpider extends INovelSpider {
	/**
	 * 如原链接： http://www.aaa.com/xiaoshuo/bbb.html 那么只需要给xiaoshuo/bbb.html即可
	 * @param url 要获取章节列表的短url
	 * @return
	 */
	public List<Chapter> getChapters(String url) throws NovelSpiderException ;
}
