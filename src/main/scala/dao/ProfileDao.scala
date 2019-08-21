package dao

import Model.Profile
import Module.DBModule
import Module.DBModule.DBContext
import javax.inject.Inject

class ProfileDao @Inject()(ctx: DBContext) {

  import ctx._

  def create(userId: Long, subName: String, bio: String, icon: String): Long = {
    val q = quote {
      query[Profile].insert(
        _.userId -> lift(userId),
        _.subName -> lift(subName),
        _.bio -> lift(bio),
        _.icon -> lift(icon)
      ).returning(_.id)
    }
    run(q)
  }

  def update(userId: Long, subName: String, bio: String, icon: String): Unit = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId))
        .update(
          _.subName -> lift(subName),
          _.bio -> lift(bio),
          _.icon -> lift(icon)
        )
    }
    run(q)
  }

  def findById(userId: Long): Option[Profile] = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId)).take(1)
    }
    run(q).headOption
  }

  def updateTweetCount(userId: Long, count: Long): Unit = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId))
        .update(i => i.tweets -> (i.tweets + lift(count)))
    }
    run(q)
  }

  def updateFollowCount(userId: Long, count: Long): Unit = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId))
        .update(i => i.following -> (i.following + lift(count)))
    }
    run(q)
  }

  def updateLikeCount(userId: Long, count: Long): Unit = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId))
        .update(i => i.likes -> (i.likes + lift(count)))
    }
    run(q)
  }


  def updateFollowedCount(userId: Long, count: Long): Unit = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId))
        .update(i => i.followed -> (i.followed + lift(count)))
    }
    run(q)
  }

  def isPublic(userId: Long): Option[Boolean] = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId)).map(_.privacy == lift("public")).take(1)
    }
    run(q).headOption
  }

}
