package com.kijimaru.twitter.domain.entity

case class Profile(
  userId: Long,
  screenName: String,
  bio: String,
  icon: String,
  tweets: Long,
  likes: Long,
  following: Long,
  followed: Long
)
