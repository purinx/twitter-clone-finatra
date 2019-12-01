package com.kijimaru.twitter.domain

import scalikejdbc.{WrappedResultSet, autoConstruct}
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

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Profile]): Profile = autoConstruct(rs, n)

}
