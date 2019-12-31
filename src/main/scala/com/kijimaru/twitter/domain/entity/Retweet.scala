package com.kijimaru.twitter.domain.entity

case class Retweet(
  id: Long,
  tweet: Tweet,
  userId: Long,
  timestamp: String
)
 