package com.kijimaru.twitter.domain.repository

import javax.inject.Inject
import com.kijimaru.twitter.domain.entity.{Follow, Tweet}
import com.kijimaru.twitter.module.DBModule.DBContext
import java.time.LocalDateTime

class TweetRepositoryImpl @Inject()(ctx: DBContext) extends TweetRepository {

  import TweetRepositoryImpl._
  import ctx._

  override def findById(id: Long): Option[Tweet] = {
    val record: Option[TweetRecord] = run {
      quote {
        querySchema[TweetRecord]("tweet")
          .filter(_.id == lift(id))
      }
    }.headOption
    record.map(_.toEntity)
  }

  override def findTimeline(userId:  Long): Either[String, List[Tweet]] = run {
    quote {
      for {
        followeeIds <- query[Follow].withFilter(_.userId == lift(userId)).map(_.followed)
        tweets <- query[Tweet].filter(tweet => liftQuery(followeeIds).contains(tweet.userId))
      } yield (followeeIds, tweets)
    }
  }
}

object TweetRepositoryImpl {

  case class TweetRecord(
    id: Long,
    userId: Long,
    userName: String,
    userSubname: String,
    userIcon: String,
    text: String,
    content: Option[String],
    liked: Int,
    retweeted: Int,
    timestamp: LocalDateTime
  ) {
    def toEntity = Tweet(
      id = id,
      userId = userId,
      userIcon = userIcon,
      text = text,
      content = content.getOrElse(""),
      liked = liked,
      retweeted = retweeted,
      timestamp = timestamp
    )
  }

}
