package com.ifengxue.novel.spider.interfaces;

import java.util.List;

import com.ifengxue.novel.spider.entity.Chapter;
import com.ifengxue.novel.spider.enums.NovelSiteEnum;
import com.ifengxue.novel.spider.exceptions.NovelDownloadException;

/**
 * 小说章节下载器
 * @author LiuKeFeng
 * @date   2016年9月27日
 */
public interface INovelDownloader {
	/** 下载失败时重试的次数 */
	public static final int TRY_TIME = 3;
	/**
	 * @param path	保存文件的路径
	 * @param novelSiteEnum		
	 * @param chapters 要下载的章节
	 * @throws Exception
	 */
	public void download(String path, NovelSiteEnum novelSiteEnum, List<Chapter> chapters) throws NovelDownloadException;
}
