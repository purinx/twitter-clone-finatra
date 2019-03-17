package Controller

import Dao.{ProfileDao, UserDao}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import io.getquill.{MysqlJdbcContext, SnakeCase}
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserManageController extends Controller {
  lazy val ctx = new MysqlJdbcContext(SnakeCase, "ctx")
  val userDao: UserDao = new UserDao
  val profileDao: ProfileDao = new ProfileDao

  get("/user/all") { request: Request =>
    userDao.getAll
  }

  get("/profile/:userId") { request: Request =>
    profileDao.findById(request.getParam("userId").toLong)
  }

  get("/user/:userId/profile") { request: Request =>
    profileDao.findById(request.getParam("userId").toLong)
  }

  //TODO Filter only user own.
  //TODO Receive icon as img file. Then save url of bucket.
  post("/user/:userId/profile/update") { request: Request =>
    profileDao.update(
      request.getParam("userId").toLong,
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
}
