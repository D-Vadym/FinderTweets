package com.finderTweets.FinderTweets.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finderTweets.FinderTweets.model.Tweets;
import com.finderTweets.FinderTweets.services.TweetsService;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@RestController
@RequestMapping("tweets")
public class TweetsController {

	private final static Logger logger = Logger.getLogger(TweetsController.class);
	private static int count = 1;

	@Autowired
	private TweetsService service;

	/**
	 * 
	 * @return all saved posts from the database
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Tweets>> getAllTweets() {
		List<Tweets> tweets = service.viewAllTweets();
		if (tweets.isEmpty()) {
			logger.info("tweets not found");
			return new ResponseEntity<List<Tweets>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("tweets is downloaded");
			return new ResponseEntity<List<Tweets>>(tweets, HttpStatus.OK);
		}
	}

	/**
	 * @param search tweets in the Twitter
	 * @param counts the number of tweets to return per page
	 * @param lang   restricts tweets to the given language, given by an
	 *               http://en.wikipedia.org/wiki/ISO_639-1
	 * @param        maxPage, return the max pages per request
	 */
	@PostMapping("/{find}")
	public ResponseEntity<Tweets> search(@RequestParam String search, @RequestParam int counts,
			@RequestParam String lang, @RequestParam int maxPage, @PathVariable String find) {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		TwitterFactory twitterFactory = new TwitterFactory(builder.build());
		Twitter twitter = twitterFactory.getInstance();
		try {
			Query query = new Query();
			query.setCount(counts);
			query.setLang(lang);
			query.setQuery(search);
			QueryResult result = twitter.search(query);
			Paging paging = new Paging(1);
			List<Status> st = result.getTweets();
			do {
				for (Status status : st) {
					service.save(new Tweets(status.getUser().getScreenName(), status.getText(), status.getCreatedAt()));

					logger.info(count + "-> " + "@" + status.getUser().getScreenName() + " : " + status.getText() + " "
							+ status.getCreatedAt() + "\n----------");
					count++;
				}
				paging.setPage(paging.getPage() + 1);
			} while (paging.getPage() != maxPage);
			logger.info(" \n to the database is recorded " + count + " tweets");
			return new ResponseEntity<Tweets>(HttpStatus.CREATED);
		} catch (TwitterException te) {
			te.printStackTrace();
			logger.error(te.getMessage());
			return new ResponseEntity<Tweets>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * removes all saved tweets from the database
	 */
	@DeleteMapping("/del")
	public ResponseEntity<Tweets> deleteTweets() {
		service.deleteAllTweets();
		count = 0;
		logger.info("Databasae is cleared");
		return new ResponseEntity<Tweets>(HttpStatus.OK);
	}
}
