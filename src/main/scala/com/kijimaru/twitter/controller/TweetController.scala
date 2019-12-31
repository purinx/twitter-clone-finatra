package com.kijimaru.twitter.controller

import com.kijimaru.twitter.domain.repository.ProfileRepository
import com.kijimaru.twitter.domain.repository.TweetRepository
import com.kijimaru.twitter.servise.TweetService
import com.twitter.finagle.http.{Request, RequestProxy}
import com.twitter.finatra.http.Controller
import javax.inject.{Inject, Singleton}

@Singleton
class TweetController @Inject() (
  profileRepository: ProfileRepository,
  tweetService: TweetService,
  tweetRepository: TweetRepository
) extends Controller {

  //TODO receive content as a file(image,text,markdown)
  post("/tweet/new") { request: Request =>
    tweetService.tweet(
      request.getParam("user_id").toLong,
      request.getParam("text"),
      Option(request.getParams("content").toString)
    )
  }

  post("/tweet/:tweetId/like") { request: Request =>
    val tweetId = request.getParam("tweetId").toLong
    val userId = request.getParam("user_id").toLong
    tweetRepository.like(userId, tweetId)
    profileRepository.updateLikeCount(userId, 1)
  }

  post("/tweet/:tweetId/retweet") { request: Request =>
    val tweetId = request.getParam("tweetId").toLong
    val userId = request.getParam("user_id").toLong
    tweetRepository.retweet(tweetId, userId)
    profileRepository.updateLikeCount(userId, 1)
  }

  post("/tweet/seed") { request: Request =>
    tweetRepository.seed()
  }
}
