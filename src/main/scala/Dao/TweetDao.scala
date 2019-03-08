package Dao

import io.getquill._
import Model.Tweet

class TweetDao {
  lazy val ctx: MysqlJdbcContext[SnakeCase.type] =
    new MysqlJdbcContext(SnakeCase, "ctx")

  import ctx._

  def findById(id: Long): Option[Tweet] = {
    val idQ = quote {
      query[Tweet].filter(i => i.id == lift(id)).take(1)
    }
    ctx.run(idQ).headOption
  }

  def findByUser(userId: Long): List[Tweet]={
    val userQ = quote{
      query[Tweet].withFilter(_.userId==lift(userId)).sortBy(_.id)
    }
    ctx.run(userQ)
  }
  def findByUserWithLimit(userId:Long, limit:Option[Int]):List[Tweet]={
    val userLimitedQ = quote{
      query[Tweet].withFilter(_.userId==lift(userId))
        .sortBy(_.id).take(lift(limit.getOrElse(100)))
    }
    ctx.run(userLimitedQ)
  }
}
