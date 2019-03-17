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

  def findByUser(userId: Long): List[Tweet] = {
    val userQ = quote {
      query[Tweet].withFilter(_.userId == lift(userId)).sortBy(_.id)
    }
    ctx.run(userQ)
  }

  def findByUserWithLimit(userId: Long, limit: Option[Int]): List[Tweet] = {
    val userLimitedQ = quote {
      query[Tweet].withFilter(_.userId == lift(userId))
        .sortBy(_.id).take(lift(limit.getOrElse(100)))
    }
    ctx.run(userLimitedQ)
  }

  def create(userId: Long, isPrivate: Boolean, text: String, content: String) = {
    val q = quote {
     query[Tweet].insert(
       _.userId->lift(userId),
       _.isPrivate->lift(isPrivate),
       _.text -> lift(text),
       _.content->lift(content)
     )
    }
    run(q)
  }

  //TODO add delete
}
