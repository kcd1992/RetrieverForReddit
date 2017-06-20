package com.kdepagter.RetrieverForReddit;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdepagter.dataobj.SubredditItem;
import com.kdepagter.processor.RedditRequestProcessor;

/**
 * Handles web service request and returns the response JSON
 * @author kyle depagter
 * @version initial release
 *
 */
@RestController
public class RedditRequestController {
	
	/**
	 * Accepts a comma delineated list of subreddits, with or with the 'r/' prefix
	 * Will return success or error response based on input and processing status
	 * @param listOfSubreddits - comma delineated String of subreddit names
	 * @return List<SubredditItem> - List of desired attributes from top posting on each subreddit
	 */
	@RequestMapping(value="/getPosts", method=RequestMethod.GET)
	public List<SubredditItem> getRedditPosts1(@RequestParam(value="listOfSubreddits", defaultValue="") String listOfSubreddits){
		RedditRequestProcessor rrp = new RedditRequestProcessor();
		List<SubredditItem> respList = rrp.processRedditRequest(listOfSubreddits);
		return respList;
	}
}
