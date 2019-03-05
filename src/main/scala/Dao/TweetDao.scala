package Dao

import io.getquill.{MysqlJdbcContext, SnakeCase}
import com.twitter.util.Future

import Model.Tweet

class TweetDao(ctx: MysqlJdbcContext[SnakeCase.type]) {

  import ctx._

  def findById(id:Long):Future[Option[Tweet]]={
    val idQ = quote(query[Tweet].filter(_.id == lift(id)).take(1))
    Future{ctx.run(idQ).headOption}
  }
}