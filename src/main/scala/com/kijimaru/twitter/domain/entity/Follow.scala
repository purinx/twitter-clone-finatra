package com.kijimaru.twitter.domain.entity

import scalikejdbc.{WrappedResultSet, autoConstruct}
import skinny.orm.{Alias, SkinnyCRUDMapper}

case class Follow(id:Long, userId:Long, followed:Long, backed:Boolean)

object Follow extends SkinnyCRUDMapper[Follow] {

  override def defaultAlias: Alias[Follow] = createAlias("f")

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Follow]): Follow = autoConstruct(rs, n)

}
