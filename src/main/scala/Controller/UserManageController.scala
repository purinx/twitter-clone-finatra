package Controller

import Dao.UserDao
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import io.getquill.{MysqlJdbcContext, SnakeCase}

class UserManageController extends Controller {
  lazy val ctx = new MysqlJdbcContext(SnakeCase, "ctx")
  val userDao: UserDao = new UserDao(ctx)

  get("/users") { request: Request =>
    userDao.getAll
  }
}