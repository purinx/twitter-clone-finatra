package Servise

import java.sql.Timestamp
import javax.inject.Inject
import dao.UserDao
import Model.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserService @Inject()(
  userDao: UserDao
) {

  lazy val bCryptPasswordEncoder: BCryptPasswordEncoder = new BCryptPasswordEncoder

  case class Login(id: java.lang.Long, token: String, timestamp: Timestamp)

  def login(name: String, password: String): Either[String, Login] = {
    val user: User = userDao.findByName(name).getOrElse(
      return Left("user not found.")
    )
    val result: Boolean = bCryptPasswordEncoder.matches(password, user.password)
    if (result) {
      //100文字のランダムな文字列
      val token = scala.util.Random.alphanumeric.take(100).mkString
      userDao.setToken(user.id, token)
      Right(Login(user.id, token, new Timestamp(System.currentTimeMillis())))
    } else {
      Left("invalid password")
    }
  }

}
