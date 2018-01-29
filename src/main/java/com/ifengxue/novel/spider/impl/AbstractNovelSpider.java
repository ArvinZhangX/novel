package com.ifengxue.novel.spider.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ifengxue.novel.spider.entity.Chapter;
import com.ifengxue.novel.spider.exceptions.CrawlException;
import com.ifengxue.novel.spider.exceptions.NovelSpiderException;
import com.ifengxue.novel.spider.interfaces.INovelSpider;
import com.ifengxue.novel.spider.util.NovelSpiderUtil;

/**
 * @author LiuKeFeng
 * @date   2016年9月18日
 */
@SuppressWarnings("unchecked")
public abstract class AbstractNovelSpider implements INovelSpider {
	/** 章节爬取规则 */
	protected static final Map<String, Map<String, Element>> allRuleMap = new HashMap<>();
	protected String domain;
	protected List<Chapter> chapters = new ArrayList<>();;
	public AbstractNovelSpider() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(NovelSpiderUtil.getRootPath() + "/Spider-Rule.xml"));
			Element root = doc.getRootElement();
			
			List<Element> novelSites = root.elements("Novel-Site");
			Map<String, Element> temp = null;
			for (Element novelSite : novelSites) {
				List<Element> childElements = novelSite.elements();
				temp = new HashMap<>();
				for (Element ele : childElements) {
					temp.put(ele.getName(), ele);
				}
				allRuleMap.put(novelSite.elementTextTrim("comment"), temp);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String crawl(String url, String charset) throws NovelSpiderException {
		String chapterUrl = domain + "/" + url;
		String crawlResult = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(chapterUrl);
		httpGet.setConfig(RequestConfig.custom()
						.setConnectionRequestTimeout(2_000)
						.setConnectTimeout(10_000)
						.setSocketTimeout(10_000)
						.build());
		NovelSpiderUtil.setDefaultNovelSpiderHeader(httpGet);
		httpGet.setHeader("Host", domain.substring(domain.indexOf("/") + 2));
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				crawlResult = EntityUtils.toString(httpResponse.getEntity(), charset);
			} else {
				throw new CrawlException("抓取失败，HTTP状态码：" + statusLine.getStatusCode());
			}
			httpResponse.close();
			httpClient.close();
			return crawlResult;
		} catch (IOException e) {
			throw new NovelSpiderException("抓取失败，失败原因：" + e.getMessage(), e);
		}
	}

	@Override
	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String getDomain() {
		return domain;
	}
}
