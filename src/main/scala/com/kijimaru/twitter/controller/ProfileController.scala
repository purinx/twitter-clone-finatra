package Controller

import com.kijimaru.twitter.domain.repository.ProfileRepository
import com.kijimaru.twitter.domain.repository.TweetRepository
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import javax.inject.Inject

class ProfileController @Inject() (
  tweetRepository: TweetRepository,
  profileRepository: ProfileRepository
) extends Controller {

  get("/user/:userId/tweets") { request: Request =>
    val userId = request.getIntParam("userId")
    tweetRepository.findByUser(userId, 0)
  }

  get("/user/:userId/profile") { request: Request =>
    val userId = request.getLongParam("userId")
    profileRepository.findById(userId)
  }
}
