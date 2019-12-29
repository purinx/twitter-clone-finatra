package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.Follow

import scala.util.Try

trait FollowRepository {

  def follow(userId: Long, followeeId: Long): Try[Unit]

  def count(userId: Long): Long
}
