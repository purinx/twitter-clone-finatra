package com.kijimaru.twitter.domain.entity

case class Profile(
  userId: Long,
  name: String,
  bio: String,
  icon: String,
  tweets: Long,
  likes: Long,
  following: Long,
  followed: Long
)
