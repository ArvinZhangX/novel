package com.ifengxue.novel.spider;

import com.ifengxue.novel.spider.enums.NovelSiteEnum;
import com.ifengxue.novel.spider.impl.chapter.BxwxNovelChapterSpider;
import com.ifengxue.novel.spider.impl.chapter.DefaultNovelChapterSpider;
import com.ifengxue.novel.spider.interfaces.INovelChapterSpider;

/**
 * 对应平台的章节抓取工厂类
 * @author LiuKeFeng
 * @date   2016年9月27日
 */
public final class ChapterFactory {
	private ChapterFactory() {}
	
	/**
	 * 获取实现了INovelChapterSpider接口的类
	 * @param novelSiteEnum
	 * @return
	 */
	public static INovelChapterSpider getChapterSpider(NovelSiteEnum novelSiteEnum) {
		switch (novelSiteEnum) {
		case BiQuGe : return new DefaultNovelChapterSpider(novelSiteEnum.getComment());
		case BiXiaWenXue : return new BxwxNovelChapterSpider(novelSiteEnum.getComment());
		case DingDianXiaoShuo : return new DefaultNovelChapterSpider(novelSiteEnum.getComment());
		default : throw new RuntimeException("尚未支持的小说站点。");
		}
	}
}
