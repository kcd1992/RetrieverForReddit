package com.kdepagter.dataobj;

/**
 * Data object representation of desired fields from each requested subreddit
 * @author kyle depagter
 * @version initial release
 *
 */
public class SubredditItem {
	private String subredditName = "";
	private String postTitle = "";
	private String postAuthor = "";
	private String createdTime = "";
	private String upvotes = "";
	private String downvotes = "";
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
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(String upvotes) {
		this.upvotes = upvotes;
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
