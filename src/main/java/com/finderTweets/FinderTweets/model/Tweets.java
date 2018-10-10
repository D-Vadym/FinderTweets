package com.finderTweets.FinderTweets.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author wadoz
 *
 */
@Document(collection = "twitter")
public class Tweets {
	@Id
	private String userName;
	private String userTweet;
	private Date dateToTweet;

	public Tweets(String userName, String userTweet, Date dateToTweet) {
		this.userName = userName;
		this.userTweet = userTweet;
		this.dateToTweet = dateToTweet;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTweet() {
		return userTweet;
	}

	public void setUserTweet(String userTweet) {
		this.userTweet = userTweet;
	}

	public Date getDateToTweet() {
		return dateToTweet;
	}

	public void setDateToTweet(Date dateToTweet) {
		this.dateToTweet = dateToTweet;
	}

}
