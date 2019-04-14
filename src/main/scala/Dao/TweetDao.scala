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
    run(idQ).headOption
  }

  def findByUser(userId: Long, offset: Int): List[Tweet] = {
    val userQ = quote {
      query[Tweet].filter(_.userId == lift(userId))
        .drop(lift(offset))
        .take(100)
    }
    run(userQ)
  }

  def userIn(userIds: List[Long], offset: Int): List[Tweet] = {
    val userQ = quote { (ids: Query[Long]) =>
      query[Tweet].filter(i => ids.contains(i.userId))
        .sortBy(_.id)(Ord.descNullsLast)
        .drop(lift(offset))
        .take(100)
    }
    run(userQ(liftQuery(userIds)))
  }

  def findByUserWithLimit(userId: Long, limit: Option[Int]): List[Tweet] = {
    val userLimitedQ = quote {
      query[Tweet].withFilter(_.userId == lift(userId))
        .sortBy(_.id).take(lift(limit.getOrElse(100)))
    }
    run(userLimitedQ)
  }

  def create(userId: Long, userName: String, userSubName: String,
             userIcon: String, text: String, content: String) = {
    val q = quote {
      query[Tweet].insert(
        _.userId -> lift(userId),
        _.userName -> lift(userName),
        _.userSubname -> lift(userSubName),
        _.userIcon -> lift(userIcon),
        _.text -> lift(text),
        _.content -> lift(content)
      )
    }
    run(q)
  }

  def like(tweetId: Long) = {
    val q = quote {
      query[Tweet].filter(tweet => tweet.id == lift(tweetId))
        .update(tweet => tweet.liked -> (tweet.liked + 1))
    }
    run(q)
  }

  def retweet(tweetId: Long) = {
    val q = quote {
      query[Tweet].filter(_.id == lift(tweetId))
        .update(tweet => tweet.retweeted -> (tweet.retweeted + 1))
    }
    run(q)
  }

  def delete(tweetId: Long) = {
    val q = quote {
      query[Tweet].filter(_.id == lift(tweetId)).delete
    }
    run(q)
  }
}
