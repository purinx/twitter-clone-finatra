package com.kijimaru.twitter.controller

import com.kijimaru.twitter.domain.repository.TweetRepository
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import javax.inject.Inject

class UserTweetController @Inject()(
  tweetRepository: TweetRepository
) extends Controller {

  get("/user/:userId/tweets") { request: Request =>
    val userId = request.getIntParam("userId")
    tweetRepository.findByUser(userId, 0)
  }
  
}
