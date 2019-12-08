package com.kijimaru.twitter.servise

import com.kijimaru.twitter.domain.entity.{Profile, User}

class TweetService {

  lazy val tweetDao = new TweetDao
  lazy val userDao = new UserDao
  lazy val profileDao = new ProfileDao

  def tweet(userId: Long, text: String, content: Option[String]):String = {
    val profile: Profile = profileDao.findById(userId)
      .getOrElse(return "user not found")
    val user:User = userDao.findById(userId)
      .getOrElse(return "user not found")

    tweetDao.create(
      userId,
      user.name,
      profile.subName,
      profile.icon,
      text,
      content.getOrElse("")
    ).toString

    profileDao.addTweetCount(userId)

    "success"
  }

  def like(userId: Long, tweetId: Long): Unit = {
    tweetDao.like(tweetId)
    likeDao.like(userId, tweetId)
    profileDao.updateLikeCount(userId, 1)
  }

  def retweet(userId: Long, tweetId: Long): Unit = {
    tweetDao.retweet(tweetId)
    retweetDao.retweet(tweetId, userId)
    profileDao.updateTweetCount(userId, 1)
  }
}
