package Dao

import Model.{Follow, Retweet}
import Module.DBModule
import Module.DBModule.DBContext
import com.twitter.util.Future
import javax.inject.Inject

class RetweetDao @Inject()(ctx: DBContext) {

  import ctx._

  def retweet(tweetId: Long, userId: Long): Future[Long] = Future {
    val q = quote {
      query[Retweet].insert(
        _.tweetId -> lift(tweetId),
        _.userId -> lift(userId)
      ).returning(_.id)
    }
    run(q)
  }

  def findByUserId(userId: Long): List[Retweet] = {
    val q = quote {
      query[Retweet]
        .withFilter(_.userId == lift(userId))
        .sortBy(_.timestamp)
        .take(100)
    }
    run(q)
  }

  def findByFollowing(userId: Long, offset: Int): List[Retweet] = {
    val q = quote {
      (for {
        following <- query[Follow].withFilter(_.userId == lift(userId))
        retweet <- query[Retweet].join(_.userId == following.followed)
      } yield retweet)
        .sortBy(_.timestamp)
        .drop(lift(offset))
        .take(100)
    }
    run(q)
  }

  def findByUserIdIn(userIds: List[Long], offset: Int): List[Retweet] = {
    val q = quote { ids: Query[Long] =>
      query[Retweet].filter(i => ids.contains(i.userId))
        .sortBy(_.id)(Ord.descNullsLast)
        .drop(lift(offset))
        .take(100)
    }
    run(q(liftQuery(userIds)))
  }
}
