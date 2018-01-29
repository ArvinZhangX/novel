package com.ifengxue.novel.spider.impl.chapter;

import java.util.List;

import com.ifengxue.novel.spider.entity.Chapter;
import com.ifengxue.novel.spider.exceptions.NovelSpiderException;
import com.ifengxue.novel.spider.impl.AbstractNovelSpider;
import com.ifengxue.novel.spider.interfaces.INovelChapterSpider;

/**
 * @author LiuKeFeng
 * @date   2016年9月17日
 */
public abstract class AbstractNovelChapterSpider extends AbstractNovelSpider implements INovelChapterSpider {
	
	protected String comment;
	/**
	 * @param url 要获取章节列表的短url
	 * @return
	 */
	public abstract List<Chapter> getChapters(String url) throws NovelSpiderException ;

	public boolean verify(Object obj) {
		Chapter vo = (Chapter) obj;
		if (vo.getHref() != null && !vo.getHref().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
