package dao

import Model.{Follow, Tweet}
import Module.DBModule.DBContext
import javax.inject.Inject

class TweetDao @Inject()(ctx: DBContext) {

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

  def findByFollowing(userId: Long, offset: Int): List[Tweet] = {
    val q = quote {
      (for {
        following <- query[Follow].withFilter(_.userId == lift(userId))
        tweet <- query[Tweet].join(_.userId == following.followed)
      } yield tweet)
        .sortBy(_.id)(Ord.descNullsLast)
        .drop(lift(offset))
        .take(100)
    }
    run(q)
  }

  def findByUserWithLimit(userId: Long, limit: Option[Int]): List[Tweet] = {
    val userLimitedQ = quote {
      query[Tweet].withFilter(_.userId == lift(userId))
        .sortBy(_.id).take(lift(limit.getOrElse(100)))
    }
    run(userLimitedQ)
  }

  def create(userId: Long, userName: String, userSubName: String,
    userIcon: String, text: String, content: String): Long = {
    val q = quote {
      query[Tweet].insert(
        _.userId -> lift(userId),
        _.userName -> lift(userName),
        _.userSubname -> lift(userSubName),
        _.userIcon -> lift(userIcon),
        _.text -> lift(text),
        _.content -> lift(content)
      ).returning(_.id)
    }
    run(q)
  }

  def like(tweetId: Long): Unit = {
    val q = quote {
      query[Tweet].filter(tweet => tweet.id == lift(tweetId))
        .update(tweet => tweet.liked -> (tweet.liked + 1))
    }
    run(q)
  }

  def retweet(tweetId: Long): Unit = {
    val q = quote {
      query[Tweet].filter(_.id == lift(tweetId))
        .update(tweet => tweet.retweeted -> (tweet.retweeted + 1))
    }
    run(q)
  }

  def delete(tweetId: Long): Unit = {
    val q = quote {
      query[Tweet].filter(_.id == lift(tweetId)).delete
    }
    run(q)
  }
}
