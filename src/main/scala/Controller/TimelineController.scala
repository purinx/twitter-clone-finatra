package Controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http._
import Dao.{FollowDao, RetweetDao, TweetDao}
import Model.User

class TimelineController extends Controller {
  val tweetDao: TweetDao = new TweetDao
  val followDao: FollowDao = new FollowDao
  val retweetDao: RetweetDao = new RetweetDao

  get("/user/:id/timeline") { request: Request =>
    val userId = request.getIntParam("id")
    val following: List[Long] = followDao.getFollowingIdList(userId)

    val tweets = following.flatMap(i => tweetDao.findByUser(i))
    val retweets = following.flatMap(i => retweetDao.findByUserId(i))
    val retweetMaped = retweets.map(i=>Map(
      "retweetBy"->i.userId,
      "tweet"->tweetDao.findById(i.tweetId),
      "timestamp"->i.timestamp
    ))
    Map("tweets"->tweets, "retweets"->retweetMaped)
  }
}
