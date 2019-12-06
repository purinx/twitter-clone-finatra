package com.kijimaru.twitter.servise

<<<<<<< HEAD:src/main/scala/Servise/TweetService.scala
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
=======
import com.kijimaru.twitter.domain.entity.{Profile, User}

class TweetService {

  lazy val tweetDao = new TweetDao
  lazy val userDao = new UserDao
  lazy val profileDao = new ProfileDao

  def tweet(userId: Long, text: String, content: Option[String]):String = {
    val profile: Profile = profileDao.findById(userId)
      .getOrElse(return "user not found")
    val user:User = userDao.findById(userId)
      .getOrElse(return "user not found")

    tweetDao.create(
      userId,
      user.name,
      profile.subName,
      profile.icon,
      text,
      content.getOrElse("")
    ).toString

    profileDao.addTweetCount(userId)

    "success"
>>>>>>> e07e1c813d9903a75bd8265a865b45b89de2e094:src/main/scala/com/kijimaru/twitter/servise/TweetService.scala
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
