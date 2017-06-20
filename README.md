# RetrieverForReddit

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
  3.) Create application level ExecutorService
