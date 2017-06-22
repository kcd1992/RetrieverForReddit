package com.kdepagter.processor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kdepagter.dataobj.SubredditItem;

/**
 * Processes web service request, obtains data from each requested subreddit.
 * @author kyle depagter
 * @version 1.1
 *
 */
public class RedditRequestProcessor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(RedditRequestProcessor.class);
	private volatile Map<String, SubredditItem> resultMap;
	private transient ExecutorCompletionService<String> ecs;
	
	public RedditRequestProcessor(){
		super();
		resultMap = new HashMap<String, SubredditItem>();
	}
	
	/**
	 * Adds Reddit responses to Thread-safe response Map
	 * @param subredditName - String name of subreddit returned for validation purposes
	 * @param subredditItem - SubredditItem representing desired fields from subreddit response
	 */
	public synchronized void addResponse(String subredditName, SubredditItem subredditItem){
		resultMap.put(subredditName, subredditItem);
		log.info(subredditName + " added to response map!");
	}
	
	/**
	 * Processes web service request, returning successful or error response as necessary
	 * @param listOfSubredditsString - comma delineated String of subreddit names
	 * @return List<SubredditItem> - List of desired attributes from top posting on each subreddit
	 */
	public List<SubredditItem> processRedditRequest(String listOfSubredditsString, ExecutorService executor){
		RedditRequestValidator validator = new RedditRequestValidator();
		List<String> subredditNameList = validator.getListOfSubreddits(listOfSubredditsString);
		List<SubredditItem> responseList = new ArrayList<SubredditItem>();
		if(subredditNameList.isEmpty()){
			responseList.add(validator.createErrorItem("At least one subreddit name is required."));
			return responseList;
		}
		long start = System.currentTimeMillis();
		try {
			callSubredditProcessors(subredditNameList, executor);
			responseList = validator.validateSubredditThreadResponses(resultMap, subredditNameList);
			resultMap = null;
		} catch (Exception e) {
			responseList.add(validator.createErrorItem("Error occurred calling Reddit. " + e.getCause() + ":" + e.getMessage()));
		}
		
		log.info("Process Time for all subreddits: " + (System.currentTimeMillis() - start));
		return responseList;

	}
	
	/**
	 * Handles execution of SubredditProcessorThread for each desired subreddit
	 * Terminates after all Threads complete or 5 seconds expire
	 * @param subredditNameList - List<String> subreddit names 
	 * @throws Exception
	 */
	private void callSubredditProcessors(List<String> subredditNameList, ExecutorService executor) throws Exception{
		ecs = new ExecutorCompletionService<String>(executor);
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		int numberOfRequests = subredditNameList.size();
		for(int i=0; i<numberOfRequests; i++){
			String currentSubreddit = subredditNameList.get(i);
			SubredditProcessorThread currentThread = new SubredditProcessorThread(currentSubreddit, this);
			futureList.add(ecs.submit(currentThread, currentSubreddit));
		}

		for(int i=0; i<numberOfRequests; i++){
			Future<String> currentResponse = ecs.poll(1, TimeUnit.SECONDS);
			if(currentResponse == null){
				log.info("Timed out waiting for subreddit response on iteration: " + Integer.toString(i));
			}
		}
		ecs = null;
	}
}
