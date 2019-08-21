package Controller

import dao.{ProfileDao, UserDao}
import Servise.UserService
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import javax.inject.Inject
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserManageController @Inject()(
  userDao: UserDao,
  profileDao: ProfileDao,
  userService: UserService
) extends Controller {


  get("/user/all") { request: Request =>
    userDao.getAll
  }

  get("/user/:userId/profile/:profileId") { request: Request =>
    val profileId = request.getLongParam("profileId")
    profileDao.isPublic(profileId) match {
      case None => "profile not found"
      case Some(true) => profileDao.findById(request.getLongParam("userId"))
      // implement private cehck
      case Some(false) => "private user"
    }
    profileDao.findById(request.getLongParam("userId"))
  }


  //TODO Filter only user own.
  //TODO Receive icon as img file. Then save url of bucket.
  post("/user/:userId/profile/update") { request: Request =>
    profileDao.update(
      request.getLongParam("userId"),
      request.getParam("name"),
      request.getParam("bio"),
      request.getParam("icon")
    )
  }

  val bCryptPasswordEncoder = new BCryptPasswordEncoder()
  post("/user/create") { request: Request =>
    val passwordHash = bCryptPasswordEncoder.encode(request.getParam("password"))
    userDao.create(
      request.getParam("id"),
      request.getParam("email"),
      passwordHash
    )
  }

  post("/user/:userId/profile/create") { request: Request =>
    profileDao.create(
      request.getParam("userId").toLong,
      request.getParam("name"),
      request.getParam("bio"),
      request.getParam("icon")
    )
  }

  post("/user/login") { request: Request =>
    userService.login(
      request.getParam("id"),
      request.getParam("password")
    )
  }
}
