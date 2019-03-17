package Controller

import Dao.{LikeDao, RetweetDao, TweetDao}
import com.twitter.finagle.http
import com.twitter.finagle.http.{ParamMap, Request, RequestProxy}
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.{JsonIgnoreBody, QueryParam, RouteParam}

class TweetController extends Controller {

  lazy val likeDao = new LikeDao

  @JsonIgnoreBody
  case class likeRequest(@RouteParam tweetId: Long, @QueryParam("user_id") userId: Long)
  post("/tweet/:tweetId/like") { request: likeRequest =>
    val tweetId = request.tweetId
    val userId = request.userId
    likeDao.like(userId, tweetId)
  }

  lazy val retweetDao = new RetweetDao


  post("/tweet/:tweetId/retweet") { request: Request =>
    val tweetId =request.getParams("tweetId").get(0).toLong
    val userId = request.getParams("user_id").get(0).toLong
    retweetDao.retweet(tweetId,userId)
  }
}
