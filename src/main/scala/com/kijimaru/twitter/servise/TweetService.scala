package com.kijimaru.twitter.servise

import javax.inject.Inject
import com.kijimaru.twitter.domain.entity.{Profile, User}
import com.kijimaru.twitter.domain.repository.{
  TweetRepository,
  RetweetRepository,
  LikeRepository,
  UserRepository,
  ProfileRepository
}

class TweetService @Inject()(
  tweetRepository: TweetRepository,
  retweetRepository: RetweetRepository,
  likeRepository: LikeRepository,
  userRepository: UserRepository,
  profileRepository: ProfileRepository
) {



  def tweet(userId: Long, text: String, content: Option[String]): String = {

    def createUser(user: User, profile: Profile): Unit = {
      tweetRepository.create(
        userId,
        text,
        content.getOrElse("")
      )
    }

    val result = for {
      profile <- profileRepository.findById(userId).toRight("user not found") // なに必要なんだろう
      user <- userRepository.findById(userId).toRight("user not found")
      _ <- Right(createUser(user, profile))
      _ <- Right(profileRepository.addTweetCount(userId))
    } yield "success"
    result.merge
  }

  def like(userId: Long, tweetId: Long): Unit = {
    tweetRepository.like(tweetId)
    likeRepository.like(userId, tweetId)
    profileRepository.updateLikeCount(userId, 1)
  }

  def retweet(userId: Long, tweetId: Long): Unit = {
    tweetRepository.retweet(tweetId)
    retweetRepository.retweet(tweetId, userId)
    profileRepository.addTweetCount(userId)
  }
}
