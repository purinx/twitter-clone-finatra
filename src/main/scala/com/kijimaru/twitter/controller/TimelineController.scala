package com.kijimaru.twitter.controller

import dao.{FollowDao, RetweetDao, TweetDao}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http._
import javax.inject.Inject

class TimelineController @Inject()(
  tweetDao: TweetDao,
  followDao: FollowDao,
  retweetDao: RetweetDao
) extends Controller {

  get("/user/:id/timeline") { request: Request =>
    val userId = request.getLongParam("id")
    val offset = Option(request.getIntParam("offset"))
    val tweets = tweetDao.findByFollowing(userId, offset.getOrElse(0))
    val retweets = retweetDao.findByFollowing(userId, offset.getOrElse(0))
    Map(
      "tweets" -> tweets,
      "retweets" -> retweets.map(i => Map(
        "user" -> i.userId,
        "tweet" -> tweetDao.findById(i.tweetId),
        "timestamp" -> i.timestamp
      ))
    )
  }
}
