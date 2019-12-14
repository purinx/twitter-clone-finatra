package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.{Tweet, Retweet}
import com.kijimaru.twitter.domain.master.ContentType

import scala.util.Try

trait TweetRepository {

  import TweetRepository._

  def create(request: CreateTweetRequest): Try[Long]

  def findById(id: Long): Option[Tweet]

  def findByUserId(userId: Long, offset: Int): Option[Tweet]

  def findByFollow(userId: Long, offset: Int): List[Tweet]

  def like(userId: Long, tweetId: Long): Try[Unit]

  def retweet(userId: Long, tweetId: Long): Either[String, Boolean]

  def findRetweetByUserId(userId: Long, offset: Int): List[Retweet]

  def findRetweetByFollow(userId: Long, offset: Int): List[Retweet]

  def seed(): Unit

}

object TweetRepository {

  case class CreateTweetRequest(
    userId: Long,
    text: String,
    contentType: ContentType,
    contentUrl: Option[String],
  )
}
