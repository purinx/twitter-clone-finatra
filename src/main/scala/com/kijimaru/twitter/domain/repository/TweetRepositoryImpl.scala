package com.kijimaru.twitter.domain.repository

import javax.inject.Inject
import com.kijimaru.twitter.domain.entity.{Follow, Tweet}
import com.kijimaru.twitter.module.DBModule.DBContext
import java.time.LocalDateTime

class TweetRepositoryImpl @Inject()(ctx: DBContext) extends TweetRepository {

  import TweetRepositoryImpl._
  import ctx._

  override def create()

  override def findById(id: Long): Option[Tweet] = {
    val record: Option[TweetRecord] = run {
      quote {
        querySchema[TweetRecord]("tweets")
          .filter(_.id == lift(id))
      }
    }.headOption
    record.map(_.toEntity)
  }

  override def findByFollow(userId: Long, offset: Int): List[Tweet] = run {
    quote {
      (for {
        follow <- query[Follow].withFilter(_.userId == lift(userId))
        tweet <- querySchema[TweetRecord]("tweets")
                    .join(_.userId == follow.followed)
      } yield tweet)
        .sortBy(_.id)
        .drop(lift(offset))
        .take(100)
    }
  }.map(_.toEntity)
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
