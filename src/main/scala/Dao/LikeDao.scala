package Dao

import io.getquill.{MysqlJdbcContext,SnakeCase}
import Model.Likes

class LikeDao {

  lazy val ctx: MysqlJdbcContext[SnakeCase.type] = new MysqlJdbcContext(SnakeCase, "ctx")

  import ctx._

  def like(userId:Long, tweetId:Long)= {
    val q = quote {
      query[Likes].insert(_.userId->lift(userId), _.tweetId->lift(tweetId))
    }
    ctx.run(q)
  }

  def findByTweet(tweetId:Long)={
    val q = quote{
      query[Likes].withFilter(_.tweetId==lift(tweetId))
    }
    ctx.run(q)
  }
}
