package com.kdepagter.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

import com.kdepagter.dataobj.SubredditItem;

/**
 * Validates input and output of web service
 * @author kyle depagter
 * @version initial release
 */
public class RedditRequestValidator {
	private static final int MAX_SUBREDDITS_ALLOWED = 5;
	
	/**
	 * Transforms comma delineated String of subreddit names into List<String>
	 * Adds 'r/' prefix to subreddit names if necessary
	 * Limits input to 5 subreddits, ignores any remaining
	 * @param listOfSubredditsString
	 * @return List<String> subreddit names
	 */
	public List<String> getListOfSubreddits(String listOfSubredditsString){
		List<String> subredditList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(listOfSubredditsString, ",");
		int currentSub = 0;
		while(st.hasMoreElements()){
			if(currentSub < MAX_SUBREDDITS_ALLOWED){
				String currentSubredditName = st.nextToken().trim();
				if(StringUtils.hasLength(currentSubredditName)){
					subredditList.add(formatSubredditName(currentSubredditName));
					currentSub++;
				}
			} else {
				break;
			}
		}
		return subredditList;
	}
	private String formatSubredditName(String subredditName){
		subredditName.toLowerCase();
		if(!subredditName.startsWith("r/")){
			subredditName = "r/" + subredditName;
		}
		return subredditName;
	}	
	
	/**
	 * Creates a new SubredditItem with an error message for error handling
	 * @param errorMessage
	 * @return SubredditItem
	 */
	public SubredditItem createErrorItem(String errorMessage){
		SubredditItem si = new SubredditItem();
		si.setErrorMessage(errorMessage);
		return si;
	}
	
	/**
	 * Validates SubredditItems returned for final return to web service response
	 * Adds Reddit responses that were completed successfully, adds error for Reddit responses not returned successfully
	 * @param subredditResponse
	 * @param subredditNameList
	 * @return
	 */
	public List<SubredditItem> validateSubredditThreadResponses(Map<String, SubredditItem> subredditResponse, List<String> subredditNameList){
		List<SubredditItem> responseList = new ArrayList<SubredditItem>();
		for(int i=0; i<subredditNameList.size(); i++){
			String currentSubreddit = subredditNameList.get(i);
			SubredditItem si = subredditResponse.get(currentSubreddit);
			if(si != null){
				responseList.add(si);
			} else {
				responseList.add(createErrorItem("Response for " + currentSubreddit + " was not returned successfully."));
			}
		}
		return responseList;
	}
}
