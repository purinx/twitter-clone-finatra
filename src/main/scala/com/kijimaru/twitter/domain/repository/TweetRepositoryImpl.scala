package com.kijimaru.twitter.domain.repository

import javax.inject.Inject
import com.kijimaru.twitter.domain.entity.{Follow, Tweet}
import com.kijimaru.twitter.module.DBModule.DBContext
import java.time.LocalDateTime

import com.kijimaru.twitter.domain.repository.TweetRepository.CreateTweetRequest

import scala.util.Try

class TweetRepositoryImpl @Inject()(ctx: DBContext) extends TweetRepository {

  import TweetRepositoryImpl._
  import ctx._

  override def create(request: CreateTweetRequest): Unit = Try {

  }

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
    text: String,
    contentUrl: String,
    createdAt: LocalDateTime
  ) {
    def toEntity = Tweet(
      id = id,
      userId = userId,
      text = text,
      contentUrl = contentUrl,
      createdAt = createdAt,
    )
  }

}
