package com.finderTweets.FinderTweets.services;

import java.util.List;

import com.finderTweets.FinderTweets.model.Tweets;

public interface ITweetService {

	List<Tweets> viewAllTweets();
	void deleteAllTweets();
	void save(Tweets tweets);
	
}
