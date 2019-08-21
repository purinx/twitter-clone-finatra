package Servise

import dao._
import com.twitter.util.Future
import javax.inject.{Inject, Singleton}

class TweetService @Inject()(
  tweetDao: TweetDao,
  userDao: UserDao,
  profileDao: ProfileDao,
  likeDao: LikeDao,
  retweetDao: RetweetDao
) {

  def tweet(
    userId: Long,
    text: String,
    content: Option[String]
  ): Future[Either[String, Long]] = Future {
    val tweetId: Option[Long] = for {
      profile <- profileDao.findById(userId)
      user <- userDao.findById(userId)
    } yield {
      tweetDao.create(
        userId,
        user.name,
        profile.subName,
        profile.icon,
        text,
        content.getOrElse("")
      )
    }
    Either.cond(tweetId.isDefined,
      tweetId.get,
      "Invalid TweetId"
    )
  }

  def like(userId: Long, tweetId: Long): Unit = {
    tweetDao.like(tweetId)
    likeDao.like(userId, tweetId)
    profileDao.updateLikeCount(userId, 1)
  }

  def retweet(userId: Long, tweetId: Long): Unit = {
    tweetDao.retweet(tweetId)
    retweetDao.retweet(tweetId, userId)
    profileDao.updateTweetCount(userId, 1)
  }
}
