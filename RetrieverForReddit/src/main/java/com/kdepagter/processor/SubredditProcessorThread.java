package com.kdepagter.processor;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.kdepagter.dataobj.SubredditItem;
import com.kdepagter.json.mapping.Children;
import com.kdepagter.json.mapping.PostData;
import com.kdepagter.json.mapping.RedditResponse;

/**
 * Obtains information from Reddit API for desired subreddit 
 * Processes Reddit JSON response and converts to SubredditItem
 * @author kyle depagter
 * @version initial release
 *
 */		
public class SubredditProcessorThread extends Thread {
	
	private static final Logger log = LoggerFactory.getLogger(SubredditProcessorThread.class);
	private String subredditName;
	private RedditRequestProcessor processor;
	
	public SubredditProcessorThread (String subredditName, RedditRequestProcessor processor) {
		this.subredditName = subredditName;
		this.processor = processor;
	}
    
	/**
	 * Prepares and executes call to Reddit API for subreddit data
	 * Processes Reddit JSON response and calls back to RedditRequestProcessor with SubredditItem
	 */
	@Override
	public void run(){
		log.info("Thread started for " + subredditName);
		long start = System.currentTimeMillis();
		RestTemplate restTemplate = new RestTemplate();
		String url = getUrlForReddit(subredditName);
		HttpEntity<String> httpEntity = setupHttpEntity();
		ResponseEntity<RedditResponse> respEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, RedditResponse.class);
		log.info("Response returned for " + subredditName);
		if(respEntity.getStatusCode().equals(HttpStatus.OK)){
			SubredditItem si = translateSubredditResponse(respEntity.getBody());
			processor.addResponse(subredditName, si);
		} else {
			RedditRequestValidator rrv = new RedditRequestValidator();
			processor.addResponse(subredditName, rrv.createErrorItem(subredditName + " returned bad HTTP response: " + respEntity.getStatusCode().toString()));
		}
		log.info("Process Time for " + subredditName + ": " + (System.currentTimeMillis() - start));
		subredditName = null;
	}
	
	/**
	 * Generates URL to call Reddit API based on subreddit name
	 * @param subredditName
	 * @return
	 */
	private static String getUrlForReddit(String subredditName){
		return "https://www.reddit.com/" + subredditName + "/top/.json?t=day&limit=1";
	}
	
	/**
	 * Prepares HTTP Headers to call Reddit API
	 * @return 
	 */
	private static HttpEntity<String> setupHttpEntity(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("User-Agent", "RetrieverForReddit-1.0.0");
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
	
	/**
	 * Handles translation of RedditResponse object to SubredditItem
	 * Handles Exception quietly by creating an error SubredditItem
	 * @param
	 * @return 
	 */
	private SubredditItem translateSubredditResponse(RedditResponse redditResponse){
		SubredditItem si = new SubredditItem();
		try {
			si = convertRedditResponseToSubredditItem(redditResponse);
		} catch (Exception e){
			si.setErrorMessage("Error occurred parsing response  for " + subredditName + ". " + e.getMessage());
		}
		return si;
	}
	
	/**
	 * Translates RedditResponse into SubredditItem 
	 * @param rr - RedditResponse
	 * @return SubredditItem
	 */
	private SubredditItem convertRedditResponseToSubredditItem(RedditResponse rr){
		SubredditItem si = new SubredditItem();
		Children[] child = rr.getTopdata().getChildren();
		PostData pd = child[0].getPostData();
		si.setSubredditName(pd.getSubredditName());
		si.setPostTitle(pd.getPostTitle());
		si.setPostAuthor(pd.getPostAuthor());
		si.setCreatedTime(processDate(pd.getCreatedDateTime()));
		si.setUpvotes(processVotes(pd.getUpvotes()));
		si.setDownvotes(processVotes(pd.getDownvotes()));
		si.setContent(pd.getContent());
		return si;
	}
	
	/**
	 * Translates Reddit created date in milliseconds to desired time format
	 * mm/dd/yyyy hh:mm (am|pm) using MST
	 * @param millis - milliseconds representing time of post creation
	 * @return desired Date format
	 */
	private String processDate(long millis){
		//Not sure why this step is necessary, appears to also be happening when calling reddit directly from browser
		millis = millis*1000;
		
		Date date = new Date(millis);
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm (a)");
		format.setTimeZone(TimeZone.getTimeZone("MST"));
		String dateString = format.format(date);
		return dateString;
	}
	
	/**
	 * Translates Reddit up/downvotes to desired format
	 * xxx,xxx,xxxx
	 * @param votes - Integer representing up/downvotes
	 * @return desired vote format
	 */
	private String processVotes(int votes){
		//Downvotes always appear to be 0, I noticed this in the browser as well
		String s = NumberFormat.getIntegerInstance().format(votes);
		return s;
	}
}
