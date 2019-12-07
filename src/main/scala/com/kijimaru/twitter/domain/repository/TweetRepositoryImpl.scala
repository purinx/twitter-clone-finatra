package com.kijimaru.twitter.domain.repository

import com.google.inject.Inject
import com.kijimaru.twitter.domain.entity.{Follow, Tweet}
import com.kijimaru.twitter.module.DBModule.DBContext

class TweetRepositoryImpl @Inject()(ctx: DBContext) extends TweetRepository {

  import ctx._

  override def findById(id: Long): Option[Tweet] = run {
    quote {
      query[Tweet].filter(_.id == lift(id))
    }
  }.headOption

  override def findTimeline(userId:  Long): Either[String, List[Tweet]] = run {
    quote {
      for {
        followeeIds <- query[Follow].withFilter(_.userId == lift(userId)).map(_.followed)
        tweets <- query[Tweet].filter(tweet => liftQuery(followeeIds).contains(tweet.userId))
      }
    }
  }
}
