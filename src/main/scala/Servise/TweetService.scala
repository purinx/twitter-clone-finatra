package Servise

import Dao._
import Model.{Profile, User}

class TweetService {

  lazy val tweetDao = new TweetDao
  lazy val userDao = new UserDao
  lazy val profileDao = new ProfileDao

  class TweetResult(_status: String) {
    val status = _status
  }

  def tweet(userId: Long, text: String, content: Option[String]): TweetResult = {
    val profile: Profile = profileDao.findById(userId)
      .getOrElse(return new TweetResult("user not found"))
    val user:User = userDao.findById(userId)
      .getOrElse(return new TweetResult("user not found"))
    val result = tweetDao.create(
      userId,
      user.name,
      profile.subName,
      profile.icon,
      text,
      content.getOrElse("")
    ).toString
    new TweetResult(result)
  }
}
