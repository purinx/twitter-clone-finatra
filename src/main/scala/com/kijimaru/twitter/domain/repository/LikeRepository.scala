package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.Likes

trait LikeRepository {

  def like(userId: Long, tweetId: Long): Unit

}
