package com.kijimaru.twitter.controller

<<<<<<< HEAD:src/main/scala/Controller/UserManageController.scala
import dao.{ProfileDao, UserDao}
import Servise.UserService
=======
import Dao.{ProfileDao, UserDao}
import com.kijimaru.twitter.servise.UserService
>>>>>>> e07e1c813d9903a75bd8265a865b45b89de2e094:src/main/scala/com/kijimaru/twitter/controller/UserManageController.scala
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
