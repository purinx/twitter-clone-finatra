package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.Tweet
import com.kijimaru.twitter.module.DBModule.DBContext

trait TweetRepository {

  def create(userId: Long, text: String, content: String): Unit // FIXME: もっと必要そうなので追加する

  def findById(id: Long): Option[Tweet]

  def findByUser(userId: Long, offset: Int): Option[Tweet]

  def findByFollowing(userId: Long, offset: Int): List[Tweet]

  def findTimeline(userId: Long): Either[String, List[Tweet]]

  def like(id: Long): Either[String, Boolean]

  def retweet(id: Long): Either[String, Boolean]

  def seed(): Unit

}
