package com.kijimaru.twitter.controller

import com.kijimaru.twitter.domain.entity.{Tweet, Retweet}
import com.kijimaru.twitter.domain.repository.TweetRepository
import com.twitter.finagle.http.Request
import com.twitter.finatra.http._
import javax.inject.Inject

class TimelineController @Inject()(
  tweetRepository: TweetRepository
) extends Controller {

  case class TimelineResponse(tweets: List[Tweet], retweets: List[Retweet])

  get("/user/:id/timeline") { request: Request =>
    val userId   = request.getLongParam("id")
    val offset   = request.getIntParam("offset")
    val tweets   = tweetRepository.findByFollow(userId, offset)
    val retweets = tweetRepository.findRetweetByFollow(userId, offset)
    TimelineResponse(tweets, retweets)
  }
}
