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
@JsonRootName("children")
public class Children {

	@JsonProperty("kind")
	private String kind = "";
	@JsonProperty("data")
	private PostData postData = new PostData();
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public PostData getPostData() {
		return postData;
	}
	public void setPostData(PostData postData) {
		this.postData = postData;
	}
}
