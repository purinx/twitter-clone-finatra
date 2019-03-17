package Controller

import Dao.{LikeDao, RetweetDao, TweetDao}
import com.twitter.finagle.http.{ParamMap, Request, RequestProxy}
import com.twitter.finatra.http.Controller


class TweetController extends Controller {

  lazy val tweetDao = new TweetDao
  //TODO receive content as file(image,text,markdown)
  post("/tweet/new") { request: Request =>
    tweetDao.create(
      request.getParam("userId").toLong,
      request.getParam("is_private").toBoolean,
      request.getParam("text"),
      request.getParam("content"),
    )
  }

  lazy val likeDao = new LikeDao
  post("/tweet/:tweetId/like") { request: Request =>
    val tweetId = request.getParam("tweetId").toLong
    val userId = request.getParam("user_id").toLong
    likeDao.like(userId, tweetId)
  }

  lazy val retweetDao = new RetweetDao
  post("/tweet/:tweetId/retweet") { request: Request =>
    val tweetId = request.getParam("tweetId").toLong
    val userId = request.getParam("user_id").toLong
    retweetDao.retweet(tweetId, userId)
  }
}
