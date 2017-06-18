package com.kdepagter.json.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditResponse {

	@JsonProperty("kind")
	private String kind = "";
	@JsonProperty("data")
	private TopData topdata = new TopData();
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public TopData getTopdata() {
		return topdata;
	}
	public void setTopdata(TopData topdata) {
		this.topdata = topdata;
	}
}
