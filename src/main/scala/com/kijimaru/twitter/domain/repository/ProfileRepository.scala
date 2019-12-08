package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.Profile

trait ProfileRepository {

  def findById(id: Long): Option[Profile]

  def updateLikeCount(userId: Long, count: Int): Unit

  def addTweetCount(userId: Long): Unit

  def isPublic(profileId: Long): Option[Boolean]

  def update(userId: Long, name: String, bio: String, icon: String): Unit

  def create(userId: Long, name: String, bio: String, icon: String): Unit

}
