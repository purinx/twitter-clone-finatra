package com.kijimaru.twitter.domain

import scalikejdbc.{WrappedResultSet, autoConstruct}
import skinny.orm.{Alias, SkinnyCRUDMapper}

case class Likes(id:Long, tweetId:Long, userId:Long)


object Likes extends SkinnyCRUDMapper[Likes] {

  override def defaultAlias: Alias[Likes] = createAlias("l")

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Likes]): Likes = autoConstruct(rs, n)

}
