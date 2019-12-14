package com.kijimaru.twitter.servise

import javax.inject.Inject
import com.kijimaru.twitter.domain.entity.{Profile, User}
import com.kijimaru.twitter.domain.repository.{
  TweetRepository,
  UserRepository,
  ProfileRepository
}

class TweetService @Inject()(
  tweetRepository: TweetRepository,
  userRepository: UserRepository,
  profileRepository: ProfileRepository
) {

  // def tweet(userId: Long, text: String, content: Option[String]): String = {
  //
  //}

  //def like(userId: Long, tweetId: Long): Unit = {
  //  tweetRepository.like(userId, tweetId)
  //}

  //def retweet(userId: Long, tweetId: Long): Unit = {
  //  tweetRepository.retweet(tweetId)
  //  retweetRepository.retweet(tweetId, userId)
  //  profileRepository.addTweetCount(userId)
  //}
}
