package com.kijimaru.twitter.domain.entity

import scalikejdbc.{ResultName, WrappedResultSet}
import skinny.orm.{Alias, SkinnyCRUDMapper}

case class Profile(
  userId: Long,
  subName: String,
  bio: String,
  icon: String,
  privacy: String,
  tweets: Long,
  likes: Long,
  following: Long,
  followed: Long
)

object Profile extends SkinnyCRUDMapper[Profile] {

  override def defaultAlias: Alias[Profile] = createAlias("p")

  override def extract(rs: WrappedResultSet, n: ResultName[Profile]): Profile = autoConstruct(rs, n)

}
