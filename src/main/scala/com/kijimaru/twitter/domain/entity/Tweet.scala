package com.kijimaru.twitter.domain.entity

import java.time.LocalDateTime

import com.kijimaru.twitter.domain.master.ContentType

case class Tweet(
  id: Long,
  userId: Long,
  userIcon: String,
  text: String,
  contentType: ContentType,
  contentUrl: String,
  createdAt: LocalDateTime
)
