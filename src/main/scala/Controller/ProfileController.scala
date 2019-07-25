package Controller


import Dao.{ProfileDao, TweetDao}
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import javax.inject.Inject

class ProfileController @Inject() (
  tweetDao: TweetDao,
  profileDao: ProfileDao
) extends Controller {

  get("/user/:userId/tweets") { request: Request =>
    val userId = request.getIntParam("userId")
    tweetDao.findByUser(userId, 0)
  }

  get("/user/:userId/profile") { request: Request =>
    val userId = request.getLongParam("userId")
    profileDao.findById(userId)
  }
}
