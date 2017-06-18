package com.kdepagter.dataobj;

import java.util.Date;

public class SubredditItem {
	private String subredditName = "";
	private String postTitle = "";
	private String postAuthor = "";
	private Date createdTime = new Date();
	private int upvotes = 0;
	private int downvotes = 0;
	private String content = "";
	private String errorMessage = "";
	
	public SubredditItem(){
		super();
	}
	
	public String getSubredditName() {
		return subredditName;
	}
	public void setSubredditName(String subredditName) {
		this.subredditName = subredditName;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostAuthor() {
		return postAuthor;
	}
	public void setPostAuthor(String postAuthor) {
		this.postAuthor = postAuthor;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public int getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
