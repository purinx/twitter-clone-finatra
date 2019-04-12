package Controller

import Dao.{LikeDao, ProfileDao, RetweetDao, TweetDao}
import Servise.TweetService
import com.twitter.finagle.http.{Request, RequestProxy}
import com.twitter.finatra.http.Controller


class TweetController extends Controller {

  lazy val profileDao = new ProfileDao
  lazy val tweetService = new TweetService
  //TODO receive content as file(image,text,markdown)
  post("/tweet/new") { request: Request =>
    tweetService.tweet(
      request.getParam("user_id").toLong,
      request.getParam("text"),
      Option(request.getParams("content").toString)
    )
  }

  lazy val likeDao = new LikeDao
  post("/tweet/:tweetId/like") { request: Request =>
    val tweetId = request.getParam("tweetId").toLong
    val userId = request.getParam("user_id").toLong
    likeDao.like(userId, tweetId)
    profileDao.addLikeCount(userId)

  }

  lazy val retweetDao = new RetweetDao
  post("/tweet/:tweetId/retweet") { request: Request =>
    val tweetId = request.getParam("tweetId").toLong
    val userId = request.getParam("user_id").toLong
    retweetDao.retweet(tweetId, userId)
  }
}
