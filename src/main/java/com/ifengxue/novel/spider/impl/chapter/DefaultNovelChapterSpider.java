package com.ifengxue.novel.spider.impl.chapter;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ifengxue.novel.spider.entity.Chapter;
import com.ifengxue.novel.spider.exceptions.NovelSpiderException;
import com.ifengxue.novel.spider.util.NovelSpiderUtil;

/**
 * @author LiuKeFeng
 * @date   2016年9月17日
 */
public class DefaultNovelChapterSpider extends AbstractNovelChapterSpider {
	public DefaultNovelChapterSpider(String comment) {
		this.comment = comment;
		this.domain = allRuleMap.get(comment).get("url").getText();
	}
	@Override
	public List<Chapter> getChapters(String url) throws NovelSpiderException {
		Map<String, org.dom4j.Element> ruleMap = allRuleMap.get(comment);
		String crawlResult = super.crawl(url, allRuleMap.get(comment).get("url").attributeValue("charset", "UTF-8"));
		Document doc = Jsoup.parse(crawlResult);
		org.dom4j.Element chapterListElement = ruleMap.get("chapter-list-element");
		Elements aTags = doc.select(chapterListElement.attributeValue("selector"));
		if (aTags == null || aTags.isEmpty()) throw new NovelSpiderException("小说章节列表解析失败。URL[" + url + "]");
		//TODO 空章节的情况？
		for(Element aTag : aTags) {
			Chapter vo = NovelSpiderUtil.parseElement2Chapter(aTag, url);
			if (verify(vo)) {
				chapters.add(vo);
			}
		}
		return chapters;
	}

	@Override
	public boolean verify(Object obj) {
		return true;
	}
}
