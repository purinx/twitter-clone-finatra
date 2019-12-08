package com.kijimaru.twitter.controller

import com.kijimaru.twitter.domain.repository.TweetRepository
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import javax.inject.Inject

class TweetSearchController @Inject() (
  tweetRepository: TweetRepository
) extends Controller {

  get("/tweet/:id") { request: Request =>
    val id = request.getIntParam("id")
    tweetRepository.findById(id)
      .getOrElse(s"Tweet id: ${id} not exists")
  }

  get("/tweets/:number") { request: Request =>
    val number = request.getIntParam("number")
    (1 to number).map(
      i => tweetRepository.findById(i).getOrElse(s"tweet id:${i} not exists")
    )
  }
}
