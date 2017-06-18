package com.kdepagter.RetrieverForReddit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdepagter.dataobj.SubredditItem;
import com.kdepagter.processor.RedditRequestProcessor;

@RestController
public class RedditRequestController {
	
	@RequestMapping(value="/getPostsFrom", method=RequestMethod.GET)
	public SubredditItem getRedditPosts(@RequestParam(value="subreddit1", defaultValue="") String subreddit1,		
			@RequestParam(value="subreddit2", defaultValue="") String subreddit2,
			@RequestParam(value="subreddit3", defaultValue="") String subreddit3,
			@RequestParam(value="subreddit4", defaultValue="") String subreddit4,
			@RequestParam(value="subreddit5", defaultValue="") String subreddit5){
		
		List<String> subreddits = generateSubredditList(subreddit1, subreddit2, subreddit3, subreddit4, subreddit5);
		SubredditItem si = new SubredditItem();
		if(subreddits.isEmpty()){
			si.setErrorMessage("A subreddit is required");
		} else {
			RedditRequestProcessor rrp = new RedditRequestProcessor();
			si = rrp.processRedditRequest(subreddits);
		}
		return si;
	}
	
	private List<String> generateSubredditList(String subreddit1, String subreddit2, 
			String subreddit3, String subreddit4, String subreddit5){
		List<String> subreddits = new ArrayList<String>();
		if(!subreddit1.isEmpty()){
			subreddits.add(subreddit1);
		}
		if(!subreddit2.isEmpty()){
			subreddits.add(subreddit2);
		}
		if(!subreddit3.isEmpty()){
			subreddits.add(subreddit3);
		}
		if(!subreddit4.isEmpty()){
			subreddits.add(subreddit4);
		}
		if(!subreddit5.isEmpty()){
			subreddits.add(subreddit5);
		}
		return subreddits;
		
	}
		
}
