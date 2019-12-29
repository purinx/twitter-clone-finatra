package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.Retweet

trait RetweetRepository {

  def findByFollow(userId: Long, offset: Int): Seq[Retweet]

  def retweet(tweetId: Long, userId: Long): Unit

  def count(tweetId: Long): Long
}
