package Controller

import Dao.{LikeDao, RetweetDao, TweetDao}
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finatra.request.{QueryParam, RouteParam}

class TweetController extends Controller {

  lazy val likeDao = new LikeDao

  case class likeRequest(@RouteParam("tweet_id") tweetId: Long, @QueryParam("user_id") userId: Long)

  post("/tweet/:tweetId/like") { request: likeRequest =>
    val tweetId = request.tweetId
    val userId = request.userId
    likeDao.like(userId, tweetId)
  }

  lazy val retweetDao = new RetweetDao
  case class retweetRequest(@RouteParam("tweet_id") tweetId:Long, @QueryParam("user_id") userId:Long)
  post("/tweet/:tweetId/retweet"){ request: retweetRequest=>
    retweetDao.retweet(request.tweetId, request.userId)
  }
}
