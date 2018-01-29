package com.ifengxue.novel.spider.impl.chapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ifengxue.novel.spider.entity.Chapter;
import com.ifengxue.novel.spider.exceptions.NovelSpiderException;

/**
 * 笔下文学小说章节爬取
 * @author LiuKeFeng
 * @date   2016年9月17日
 */
public class BxwxNovelChapterSpider extends DefaultNovelChapterSpider {
	public BxwxNovelChapterSpider(String comment) {
		super(comment);
	}
	@Override
	public List<Chapter> getChapters(String url) throws NovelSpiderException {
		chapters = super.getChapters(url);
		Collections.sort(chapters, new Comparator<Chapter>() {	//笔下文学爬取的章节需要重新排序
			@Override
			public int compare(Chapter o1, Chapter o2) {
				return o1.getHref().compareTo(o2.getHref());
			}
		});
		return chapters;
	}
	public static void main(String[] args) throws NovelSpiderException {
		BxwxNovelChapterSpider spider = new BxwxNovelChapterSpider("bxwx8.org");
		List<Chapter> vos = spider.getChapters("b/70/70093");
		System.out.println("章节数量：" + vos.size());
		for (Chapter vo : vos) {
			System.out.println(vo.getText() + "[" + vo.getHref() + "]");
		}
	}
}
