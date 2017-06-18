package com.kdepagter.processor;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.kdepagter.dataobj.SubredditItem;
import com.kdepagter.json.mapping.RedditResponse;

public class RedditRequestProcessor {
	
	public RedditRequestProcessor(){
		super();
	}
	
	public SubredditItem processRedditRequest(List<String> subredditNames){
		SubredditItem si = new SubredditItem();
		for(int i=0;i<subredditNames.size();i++){
			String subredditName = subredditNames.get(i);
			si = getAndConvertSubredditItem(subredditName);
		}
		return si;
	}
	
	private SubredditItem getAndConvertSubredditItem(String subredditName){
		RestTemplate restTemplate = new RestTemplate();
		String url = getUrlForReddit(subredditName);
		SubredditItem si = new SubredditItem();
		try {
			RedditResponse rr = restTemplate.getForObject(url, RedditResponse.class);
			si = convertRedditResponseToSubredditItem(rr);
		} catch (Exception e){
			si.setErrorMessage("Error occurred while obtaining data from reddit for " + subredditName + ". " + e.getMessage());
		}
		
		return si;
	}
	
	//TODO check for r/ in validation
	private String getUrlForReddit(String subredditName){
		return "https://reddit.local/r/" + subredditName + "/.json?limit=1";
	}
	
	private SubredditItem convertRedditResponseToSubredditItem(RedditResponse rr){
		SubredditItem si = new SubredditItem();
		return si;
	}
}
