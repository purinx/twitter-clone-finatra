package com.kijimaru.twitter.domain.entity

case class Follow(
  id: Long,
  userId: Long,
  followed: Long,
  backed: Boolean
)
