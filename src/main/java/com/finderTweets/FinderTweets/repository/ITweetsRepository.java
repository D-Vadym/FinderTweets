package com.finderTweets.FinderTweets.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.finderTweets.FinderTweets.model.Tweets;

@Repository
public interface ITweetsRepository extends MongoRepository<Tweets, String> {

}
