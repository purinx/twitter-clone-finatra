package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.Retweet

trait RetweetRepository {

  def findByFollowing(userId: Long, offset: Int): Seq[Retweet]

  def retweet(tweetId: Long, userId: Long): Unit

}
