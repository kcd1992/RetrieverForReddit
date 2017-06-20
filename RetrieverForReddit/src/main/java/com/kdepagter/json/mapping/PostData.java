package com.kdepagter.json.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Data object for parsing sub level of Reddit JSON response
 * @author kyle depagter
 * @version initial release
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("data")
public class PostData {
	
	@JsonProperty("subreddit_name_prefixed")
	private String subredditName = "";
	@JsonProperty("author")
	private String postAuthor = "";
	@JsonProperty("downs")
	private int downvotes =0;
	@JsonProperty("url")
	private String content = "";
	@JsonProperty("title")
	private String postTitle = "";
	@JsonProperty("created")
	private long createdDateTime = 1L;
	@JsonProperty("ups")
	private int upvotes = 0;
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
	public int getDownvotes() {
		return downvotes;
	}
	public void setDownvotes(int downvotes) {
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
	public long getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(long createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public int getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
	
}
