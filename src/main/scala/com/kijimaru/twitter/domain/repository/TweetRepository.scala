package com.kijimaru.twitter.domain.repository

import com.google.inject.Inject
import com.kijimaru.twitter.domain.entity.Tweet
import com.kijimaru.twitter.module.DBModule.DBContext

class TweetRepository {

  def findById(id: Long): Option[Tweet]

  def findTimeline(userId: Long): Either[String, List[Tweet]]

  def like(id: Long): Either[String, Boolean]

  def retweet(id: Long): Either[String, Boolean]

}
