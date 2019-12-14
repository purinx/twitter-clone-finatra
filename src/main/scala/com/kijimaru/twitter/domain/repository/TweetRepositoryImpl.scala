package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.{Follow, Tweet}
import com.kijimaru.twitter.domain.master.ContentType
import com.kijimaru.twitter.module.DBModule.DBContext
import java.time.LocalDateTime
import javax.inject.Inject

import scala.util.Try

class TweetRepositoryImpl @Inject()(ctx: DBContext) extends TweetRepository {

  import TweetRepository._
  import TweetRepositoryImpl._
  import ctx._

  override def create(request: CreateTweetRequest): Try[Long] = Try {
    run (
      quote {
        querySchema[TweetRecord]("tweets")
          .insert(
            _.userId -> lift(request.userId),
            _.text -> lift(request.text),
            _.contentTypeId -> lift(request.contentType.getValue),
            _.contentUrl -> lift(request.contentUrl)
          )
      }
    )
  }

  override def findById(id: Long): Option[Tweet] = {
    val record: Option[(TweetRecord, ProfileRecord)] = run {
      quote {
        querySchema[TweetRecord]("tweets")
          .filter(_.id == lift(id))
          .join(querySchema[ProfileRecord]("profiles"))
            .on((t, p) => t.userId == p.userId)
      }
    }.headOption
    record.map(r => createTweetEntity(r._1, r._2))
  }

  override def findByUserId(userId: Long, offset: Int): List[Tweet] = run {
    quote {
      querySchema[TweetRecord]("tweets")
        .withFilter(_.userId == lift(userId))
        .join(querySchema[ProfileRecord]("profiles"))
        .on((t, p) => t.userId == p.userId)
        .drop(lift(offset))
        .take(100)
    }
  }.map(r => createTweetEntity(r._1, r._2))

  override def findByFollow(userId: Long, offset: Int): List[Tweet] = {
    val record: List[(TweetRecord, ProfileRecord)] = run {
      quote {
        (for {
          follow <- query[Follow]
            .withFilter(_.userId == lift(userId))
          tweet <- querySchema[TweetRecord]("tweets")
            .join(_.userId == follow.followed)
          profile <- querySchema[ProfileRecord]("profile")
            .join(_.userId == tweet.userId)
        } yield (tweet, profile))
          .sortBy(_._1.id)
          .drop(lift(offset))
          .take(100)
      }
    }
    record.map(r => createTweetEntity(r._1, r._2))
  }

  override def like(userId: Long, tweetId: Long): Try[Unit] = Try {
    run (
      quote {
        querySchema[LikeRecord]("likes").insert(
          _.tweetId -> lift(tweetId),
          _.userId -> lift(userId)
        )
      }
    )
  }

  override def retweet(userId: Long, tweetId: Long): Try[Unit] = Try {
    run (
      quote {
        querySchema[RetweetRecord]("retweets").insert(
          _.tweetId -> lift(tweetId),
          _.userId -> lift(userId)
        )
      }
    )
  }

  override def findRetweetByUserId(userId: Long, offset: Int): List[Retweet] = run {
    quote {
      querySchema[RetweetRecord]("retweets")
        .withFilter(_.userId == lift(userId))
        .drop(lift(offset))
        .take(100)
    }
  }

  override def findRetweetByFollow(userId: Long, offset: Int): List[Retweet] = run {
    
  }

  override def seed() = { }

}

object TweetRepositoryImpl {

  case class TweetRecord(
    id: Long,
    userId: Long,
    text: String,
    contentTypeId: Int,
    contentUrl: Option[String],
    createdAt: LocalDateTime
  )

  case class ProfileRecord(userId: Long, icon: String)

  def createTweetEntity(tweet: TweetRecord, profile: ProfileRecord) = Tweet(
    id = tweet.id,
    userId = tweet.userId,
    userIcon = profile.icon,
    text = tweet.text,
    contentType = ContentType.fromId(tweet.contentTypeId), // TODO
    contentUrl = tweet.contentUrl,
    createdAt = tweet.createdAt,
  )

  case class LikeRecord(
    id: Long,
    userId: Long,
    tweetId: Long
  )

  case class RetweetRecord(
    id: Long,
    userId: Long,
    tweetId: Long
  )
}
