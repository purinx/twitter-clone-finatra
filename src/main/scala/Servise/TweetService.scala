package Servise

import Dao.{LikeDao, ProfileDao, RetweetDao, TweetDao}
import Model.Profile

class TweetService {


  lazy val profileDao = new ProfileDao
  class TweetResult(_status:String){
    val status = _status
  }
  def tweet(userId:Long , text:String, content:Option[String]):TweetResult={
    val user:Profile = profileDao.findById(userId)
      .getOrElse(return new TweetResult("user not found"))

  }
}
