package com.glens.pwCloudOs.weixin.bean;

public class Articles {

	private String title;

	private String thumb_media_id;

	private String author;

	private String content_source_url;

	private String content;

	private String digest;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	public String getThumb_media_id() {
		return this.thumb_media_id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}

	public String getContent_source_url() {
		return this.content_source_url;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getDigest() {
		return this.digest;
	}
}
