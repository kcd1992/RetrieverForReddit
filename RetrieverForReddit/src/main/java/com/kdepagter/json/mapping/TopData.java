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
public class TopData {
	
	@JsonProperty("modhash")
	private String modhash = "";
	@JsonProperty("children")
	private Children[] children = new Children[1];
	public String getModhash() {
		return modhash;
	}
	public void setModhash(String modhash) {
		this.modhash = modhash;
	}
	public Children[] getChildren() {
		return this.children;
	}
	public void setChildren(Children[] children) {
		this.children = children;
	}
	
}
