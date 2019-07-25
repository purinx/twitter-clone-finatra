package Dao

import Model.Likes
import Module.DBModule
import Module.DBModule.DBContext
import javax.inject.Inject

class LikeDao @Inject() (ctx: DBContext) {

  import ctx._

  def like(userId: Long, tweetId: Long): Unit = {
    val q = quote {
      query[Likes].insert(_.userId -> lift(userId), _.tweetId -> lift(tweetId))
    }
    run(q)
  }

  def findByTweet(tweetId: Long): Unit = {
    val q = quote {
      query[Likes].withFilter(_.tweetId == lift(tweetId))
    }
    run(q)
  }
}
