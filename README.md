# RetrieverForReddit

This is a service that will return certain data for the top posting on any five subreddits on Reddit.com for the past 24 hours. The data elements returned are:

    1.) Subreddit name
    2.) Post title
    3.) Post author
    4.) Post created time 
    5.) Upvote count
    6.) Downvote count
    7.) Content URL
  
Usage:
URL: localhost:8080/getPosts?listOfSubreddits=subreddit1, subreddit2
  
  Requirements for listOfSubreddits:
    
      1.) The field should be entered as a comma delineated String.
      2.) At least one subreddit is required.
      3.) Service will process up to 5, any additional will be ignored.
      4.) Subreddit names can be with or without the 'r/' prefix.
    
Future enhancements:
  
    1.) Utilize unit testing
    2.) Provide more descriptive error handling
    3.) Create application level ExecutorService - complete v. 1.1
    4.) Offer searching for top post of other than 24 hours.
