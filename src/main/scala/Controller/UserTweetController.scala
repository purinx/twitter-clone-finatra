package Controller

import Dao.TweetDao
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

class UserTweetController extends Controller {
  lazy val tweetDao = new TweetDao
  get("/user/:userId/tweets") { request: Request =>
    val userId = request.getIntParam("userId")
    tweetDao.findByUser(userId)
  }
}
