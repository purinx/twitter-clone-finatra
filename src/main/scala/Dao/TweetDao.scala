package Dao

import io.getquill.{MysqlJdbcContext, SnakeCase}

import Model.Tweet

class TweetDao(ctx: MysqlJdbcContext[SnakeCase.type]) {

  import ctx._

  def findById(id:Long):Option[Tweet]={
    val idQ = quote{query[Tweet].filter(i=> i.id == lift(id)).take(1)}
    ctx.run(idQ).headOption
  }
}