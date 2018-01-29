package com.ifengxue.novel.spider.exceptions;

/**
 * 小说下载异常
 * @author LiuKeFeng
 * @date   2016年9月26日
 */
public class NovelDownloadException extends NovelSpiderException {
	private static final long serialVersionUID = -1141885897383743245L;

	public NovelDownloadException() {
	}

	public NovelDownloadException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NovelDownloadException(String message, Throwable cause) {
		super(message, cause);
	}

	public NovelDownloadException(String message) {
		super(message);
	}

	public NovelDownloadException(Throwable cause) {
		super(cause);
	}

}
