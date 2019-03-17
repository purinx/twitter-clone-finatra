package Servise

import Dao.{TweetDao,RetweetDao,LikeDao}

class TweetService {

  lazy val tweetDao = new TweetDao
  lazy val retweetDao = new RetweetDao
  lazy val likeDao = new LikeDao

  def retweet(tweetId:Long,userId:Long)={

  }
}
