package com.ifengxue.novel.spider.impl.content;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ifengxue.novel.spider.entity.Content;
import com.ifengxue.novel.spider.exceptions.CrawlException;
import com.ifengxue.novel.spider.exceptions.NovelSpiderException;
import com.ifengxue.novel.spider.impl.AbstractNovelSpider;
import com.ifengxue.novel.spider.interfaces.INovelContentSpider;
import com.ifengxue.novel.spider.util.NovelSpiderUtil;

/**
 * @author LiuKeFeng
 * @date   2016年9月18日
 */
public abstract class AbstractNovelContentSpider extends AbstractNovelSpider implements INovelContentSpider {
	/** 前一章,下一章解析规则 */
	private static final String CHAPTER_MATCH_RULE = ".*/*\\d+\\.[html]{3,4}";
	protected Document parseDocument;
	protected Content content = new Content();
	protected String simpleDomain;
	/**
	 * 解析并加工抓取的页面内容
	 * @param crawlString
	 * @return
	 * @throws NovelSpiderException 
	 */
	@SuppressWarnings("unchecked")
	protected String resolveCrawlString(String crawlString) throws NovelSpiderException {
		Map<String, org.dom4j.Element> ruleMap = allRuleMap.get(simpleDomain);
		org.dom4j.Element thisElement = ruleMap.get("content-this-element");
		List<org.dom4j.Element> parseElements = thisElement.elements("parse");
		if (parseElements != null && !parseElements.isEmpty()) {
			for (org.dom4j.Element parseElement : parseElements) {
				crawlString = crawlString.replaceAll(parseElement.getText(), parseElement.attributeValue("to") == null ? "" : parseElement.attributeValue("to"));
			}
		}
		parseDocument = Jsoup.parse(crawlString);
		Elements elements = parseDocument.select(thisElement.attributeValue("selector"));
		if (elements == null || elements.isEmpty()) throw new NovelSpiderException("抓取规则不正确！");
		int index = thisElement.attributeValue("index") == null ? 0 : Integer.parseInt(thisElement.attributeValue("index"));
		crawlString = elements.get(index).text();
		if (parseElements != null && !parseElements.isEmpty()) {
			for (org.dom4j.Element parseElement : parseElements) {
				crawlString = NovelSpiderUtil.replaceSpecifyString(crawlString, parseElement.attributeValue("to"));
			}
		}
		return crawlString;
	}
	
	/**
	 * 获取章节内容
	 * @param url 短链接
	 * @return
	 * @throws NovelSpiderException
	 */
	public Content getContent(String url) throws NovelSpiderException {
		Content vo = new Content();
		String resolve = resolveCrawlString(super.crawl(url, allRuleMap.get(simpleDomain).get("url").attributeValue("charset")));
		vo.setContent(resolve);
		vo.setTitle(crawlTitle());
		vo.setPrev(crawlPrevChapter());
		vo.setNext(crawlNextChapter());
		return vo;
	}
	
	@Override
	public boolean verify(Object obj) {
		return true;
	}

	/**
	 * 从爬取的章节内容中解析文章标题
	 * @param content
	 * @return
	 * @throws CrawlException
	 */
	public String crawlTitle() throws CrawlException {
		org.dom4j.Element element = allRuleMap.get(simpleDomain).get("content-this-title-element");
		String selector = element.attributeValue("selector");
		int index = element.attributeValue("index") == null ? 0 : Integer.parseInt(element.attributeValue("index"));
		Element titleElement = parseDocument.select(selector).get(index);
		if (titleElement == null) {
			return "";
		} else {
			return titleElement.text();
		}
	}

	/**
	 * 从爬取的章节内容中解析上一章URL
	 * @param content
	 * @return
	 * @throws CrawlException
	 */
	public String crawlPrevChapter() throws CrawlException {
		org.dom4j.Element element = allRuleMap.get(simpleDomain).get("content-this-prev-element");
		String selector = element.attributeValue("selector");
		int index = element.attributeValue("index") == null ? 0 : Integer.parseInt(element.attributeValue("index"));
		Element prevElement = parseDocument.select(selector).get(index);
		if (prevElement == null || !prevElement.attr("href").matches(CHAPTER_MATCH_RULE)) {
			return "";
		} else {
			String href = prevElement.attr("href");
			return href;
		}
	}

	/**
	 * 从爬取的章节内容中解析下一章URL
	 * @param content
	 * @return
	 * @throws CrawlException
	 */
	public String crawlNextChapter() throws CrawlException {
		org.dom4j.Element element = allRuleMap.get(simpleDomain).get("content-this-next-element");
		String selector = element.attributeValue("selector");
		int index = element.attributeValue("index") == null ? 0 : Integer.parseInt(element.attributeValue("index"));
		Element nextElement = parseDocument.select(selector).get(index);
		if (nextElement == null || !nextElement.attr("href").matches(CHAPTER_MATCH_RULE)) {
			return "";
		} else {
			return nextElement.attr("href");
		}
	}
	
}
