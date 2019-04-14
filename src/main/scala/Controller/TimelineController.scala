package Controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http._
import Dao.{FollowDao, RetweetDao, TweetDao}

class TimelineController extends Controller {
  val tweetDao: TweetDao = new TweetDao
  val followDao: FollowDao = new FollowDao
  val retweetDao: RetweetDao = new RetweetDao

  get("/user/:id/timeline") { request: Request =>
    val userId = request.getIntParam("id")
    val following: List[Long] = followDao.getFollowingIdList(userId)
    val tweets = tweetDao.userIn(following, 0)
    val retweets = retweetDao.userIn(following, 0)

    Map(
      "tweets" -> tweets,
      "retweets" -> retweets.map(i => Map(
        "user" -> i.userId,
        "tweet" -> tweetDao.findById(i.tweetId),
        "timestamp" -> i.timestamp
      ))
    )
  }
}
