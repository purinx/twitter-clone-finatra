package com.kijimaru.twitter.domain.entity

import com.kijimaru.twitter.domain.master.ContentType
import java.time.LocalDateTime

case class Tweet(
  id: Long,
  userId: Long,
  userIcon: String,
  text: String,
  contentType: ContentType,
  contentUrl: Option[String],
  createdAt: LocalDateTime
)
