package com.kijimaru.twitter.domain.repository

trait LikeRepository {

  def like(userId: Long, tweetId: Long): Unit

  def count(tweetId: Long): Unit

}
