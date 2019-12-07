package com.kijimaru.twitter.domain.entity

case class Tweet(
  id: Long,
  userId: Long,
  userIcon: String,
  text: String,
  content: String,
  liked: Long,
  retweeted: Long,
  timestamp: String
)
