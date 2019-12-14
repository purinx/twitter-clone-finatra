package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.Tweet
import com.kijimaru.twitter.domain.master.ContentType

trait TweetRepository {

  import TweetRepository._

  def create(request: CreateTweetRequest): Unit

  def findById(id: Long): Option[Tweet]

  def findByUser(userId: Long, offset: Int): Option[Tweet]

  def findByFollow(userId: Long, offset: Int): List[Tweet]

  def findTimeline(userId: Long): Either[String, List[Tweet]]

  def like(id: Long): Either[String, Boolean]

  def retweet(id: Long): Either[String, Boolean]

  def seed(): Unit

}

object TweetRepository {

  case class CreateTweetRequest(
    userId: Long,
    text: String,
    content: ContentType,
  )

}
