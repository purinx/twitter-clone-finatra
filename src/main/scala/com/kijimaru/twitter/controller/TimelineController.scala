package com.kijimaru.twitter.controller

import com.kijimaru.twitter.domain.repository.FollowRepository
import com.kijimaru.twitter.domain.repository.TweetRepository
import com.kijimaru.twitter.domain.repository.RetweetRepository
import com.twitter.finagle.http.Request
import com.twitter.finatra.http._
import javax.inject.Inject

class TimelineController @Inject()(
  tweetRepository: TweetRepository,
  followRepository: FollowRepository,
  retweetRepository: RetweetRepository
) extends Controller {

  get("/user/:id/timeline") { request: Request =>
    val userId = request.getLongParam("id")
    val offset = Option(request.getIntParam("offset"))
    val tweets = tweetRepository.findByFollowing(userId, offset.getOrElse(0))
    val retweets = retweetRepository.findByFollowing(userId, offset.getOrElse(0))
    Map(
      "tweets" -> tweets,
      "retweets" -> retweets.map(i => Map(
        "user" -> i.userId,
        "tweet" -> tweetRepository.findById(i.tweetId), // FIXME, viewの構築中に副作用を起こさないように
        "timestamp" -> i.timestamp
      ))
    )
  }
}
