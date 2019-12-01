package com.kijimaru.twitter.domain


import scalikejdbc.{WrappedResultSet, autoConstruct}
import skinny.orm.{Alias, SkinnyCRUDMapper}

case class User(id:Long, password:String, name:String, email:String, token:String)

object User extends SkinnyCRUDMapper[User] {

  override def defaultAlias: Alias[User] = createAlias("u")

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[User]): User = autoConstruct(rs, n)

}
