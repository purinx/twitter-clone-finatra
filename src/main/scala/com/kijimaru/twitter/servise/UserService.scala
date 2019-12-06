package com.kijimaru.twitter.servise

<<<<<<< HEAD:src/main/scala/Servise/UserService.scala
import java.sql.Timestamp
import javax.inject.Inject
import dao.UserDao
import Model.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserService @Inject()(
  userDao: UserDao
) {
=======
import com.google.inject.Inject
import com.kijimaru.twitter.domain.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserService @Inject()(
  user: User
){
>>>>>>> e07e1c813d9903a75bd8265a865b45b89de2e094:src/main/scala/com/kijimaru/twitter/servise/UserService.scala

  lazy val bCryptPasswordEncoder: BCryptPasswordEncoder = new BCryptPasswordEncoder

  case class Login(id: java.lang.Long, token: String, timestamp: Timestamp)

<<<<<<< HEAD:src/main/scala/Servise/UserService.scala
  def login(name: String, password: String): Either[String, Login] = {
    val user: User = userDao.findByName(name).getOrElse(
      return Left("user not found.")
=======
  def login(email: String, password: String): loginResult = {
    val user: User = user.whe.getOrElse(
      return new loginResult("userNotFound",null, "")
>>>>>>> e07e1c813d9903a75bd8265a865b45b89de2e094:src/main/scala/com/kijimaru/twitter/servise/UserService.scala
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
