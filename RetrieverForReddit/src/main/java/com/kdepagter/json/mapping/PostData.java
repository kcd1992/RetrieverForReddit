package com.kdepagter.json.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostData {
	
	@JsonProperty("subreddit_name_prefixed")
	private String subredditName = "";
	@JsonProperty("author")
	private String postAuthor = "";
	@JsonProperty("downs")
	private String downvotes = "";
	@JsonProperty("url")
	private String content = "";
	@JsonProperty("title")
	private String postTitle = "";
	@JsonProperty("created_utc")
	private String createdDateTime = "";
	@JsonProperty("ups")
	private String upvotes = "";
	public String getSubredditName() {
		return subredditName;
	}
	public void setSubredditName(String subredditName) {
		this.subredditName = subredditName;
	}
	public String getPostAuthor() {
		return postAuthor;
	}
	public void setPostAuthor(String postAuthor) {
		this.postAuthor = postAuthor;
	}
	public String getDownvotes() {
		return downvotes;
	}
	public void setDownvotes(String downvotes) {
		this.downvotes = downvotes;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(String upvotes) {
		this.upvotes = upvotes;
	}
	
}
