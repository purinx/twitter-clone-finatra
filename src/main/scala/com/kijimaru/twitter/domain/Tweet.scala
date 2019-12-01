package com.kijimaru.twitter.domain

import scalikejdbc.{WrappedResultSet, autoConstruct}
import skinny.orm.{Alias, SkinnyCRUDMapper}

case class Tweet(
                  id: Long,
                  userId: Long,
                  userName: String,
                  userSubname: String,
                  userIcon: String,
                  text: String,
                  content: String,
                  liked: Long,
                  retweeted: Long,
                  timestamp: String
                )

object Tweet extends SkinnyCRUDMapper[Tweet] {

  override def defaultAlias: Alias[Tweet] = createAlias("t")

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Tweet]): Tweet = autoConstruct(rs, n)

}
