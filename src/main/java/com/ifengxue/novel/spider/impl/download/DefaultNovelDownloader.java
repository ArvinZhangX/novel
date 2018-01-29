package com.ifengxue.novel.spider.impl.download;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ifengxue.novel.spider.ContentFactory;
import com.ifengxue.novel.spider.entity.Chapter;
import com.ifengxue.novel.spider.entity.Content;
import com.ifengxue.novel.spider.enums.NovelSiteEnum;
import com.ifengxue.novel.spider.exceptions.NovelDownloadException;
import com.ifengxue.novel.spider.exceptions.NovelSpiderException;
import com.ifengxue.novel.spider.interfaces.INovelContentSpider;
import com.ifengxue.novel.spider.interfaces.INovelDownloader;
import com.ifengxue.util.common.CollectionTool;
import com.ifengxue.util.common.CommTool;

/**
 * @author LiuKeFeng
 * @date   2016年9月27日
 */
public class DefaultNovelDownloader implements INovelDownloader {

	@Override
	public void download(String path, NovelSiteEnum novelSiteEnum, List<Chapter> chapters)
			throws NovelDownloadException {
		PrintWriter out = null;
		try {
			out = new PrintWriter(path, "GBK");
			assert !CollectionTool.isNotNullAndEmpty(chapters) : "章节列表不应为null或为size=0";
			if (CollectionTool.isNotNullAndEmpty(chapters)) {
				for (Chapter chapter : chapters) {
					Content content = null;
					try {
						content = tryAndDownload(novelSiteEnum, chapter.getHref());
					} catch (NovelSpiderException e) {
						//尝试多次下载失败了
						content = new Content();
						content.setTitle(chapter.getText());
						content.setContent("很抱歉，" + e.getMessage());
					}
					out.println(content.getTitle());
					out.println(content.getContent());
					CommTool.sleep(1_000);	//下载不要太频繁
				}
			}
		} catch (IOException e) {
			throw new NovelDownloadException(e);
		} finally {
			out.close();
		}
	}
	
	/**
	 * 多次尝试下载
	 * @param novelSiteEnum
	 * @param url
	 * @return
	 * @throws NovelSpiderException
	 */
	public Content tryAndDownload(NovelSiteEnum novelSiteEnum, String url) throws NovelSpiderException {
		INovelContentSpider contentSpider = ContentFactory.getContentSpider(novelSiteEnum);
		Content content = null;
		for (int i = 1; i <= TRY_TIME; i++) {
			try {
				content = contentSpider.getContent(url);
				break;	//下载成功
			} catch (NovelSpiderException e) {
				// 重试
			}
		}
		if (content != null) {
			return content;
		} else {
			throw new NovelSpiderException("尝试" + TRY_TIME + "次下载均失败了！");
		}
	}
}
