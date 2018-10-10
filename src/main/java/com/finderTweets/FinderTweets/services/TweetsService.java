package com.finderTweets.FinderTweets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finderTweets.FinderTweets.model.Tweets;
import com.finderTweets.FinderTweets.repository.ITweetsRepository;

@Service
public class TweetsService implements ITweetService {

	@Autowired
	private ITweetsRepository repos;

	@Override
	public List<Tweets> viewAllTweets() {
		return repos.findAll();
	}

	@Override
	public void deleteAllTweets() {
		repos.deleteAll();
	}

	@Override
	public void save(Tweets tweets) {
		repos.save(tweets);
	}
}
