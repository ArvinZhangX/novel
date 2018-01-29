package com.ifengxue.novel.spider.junit.test;

import java.util.List;

import org.junit.Test;

import com.ifengxue.novel.spider.ChapterFactory;
import com.ifengxue.novel.spider.ContentFactory;
import com.ifengxue.novel.spider.entity.Chapter;
import com.ifengxue.novel.spider.entity.Content;
import com.ifengxue.novel.spider.enums.NovelSiteEnum;
import com.ifengxue.novel.spider.exceptions.NovelSpiderException;
import com.ifengxue.novel.spider.impl.download.NovelDownloaderObserver;
import com.ifengxue.novel.spider.interfaces.INovelChapterSpider;
import com.ifengxue.novel.spider.interfaces.INovelContentSpider;
import com.ifengxue.novel.spider.util.NovelSpiderUtil;

/**
 * @author LiuKeFeng
 * @date   2016年10月1日
 */
public class NovelSpiderTest {
	@Test
	public void getChapters() throws NovelSpiderException {
		NovelSpiderUtil.setRootPath("D:/project/novelspider");
		String url = "http://www.biquge.tw/0_5/";
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		INovelChapterSpider spider = ChapterFactory.getChapterSpider(novelSiteEnum);
		List<Chapter> chapters = spider.getChapters(NovelSpiderUtil.getRelativeUrl(novelSiteEnum, url));
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}
	@Test
	public void getContent() throws NovelSpiderException {
		NovelSpiderUtil.setRootPath("D:/project/novelspider");
		String url = "http://www.biquge.tw/0_5/910022.html";
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		INovelContentSpider spider = ContentFactory.getContentSpider(novelSiteEnum);
		Content content = spider.getContent(NovelSpiderUtil.getRelativeUrl(novelSiteEnum, url));
		System.out.println(content);
	}
	public static void main(String[] args) {
		// http://www.bxwx8.org/b/70/70093
		// http://www.biquge.tw/0_5/
		// http://www.23wx.com/html/42/42377/
		NovelSpiderUtil.setRootPath("D:/project/novelspider");
		String url = "http://www.bxwx8.org/b/70/70093/index.html";
		NovelDownloaderObserver observer = new NovelDownloaderObserver(url);
		observer.process();
		System.out.println("合并后的文件地址：" + NovelSpiderUtil.getNovelMergePath(url));
	}
}
