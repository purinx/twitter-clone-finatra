package com.kijimaru.twitter.servise

import com.google.inject.Inject
import com.kijimaru.twitter.domain.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserService @Inject()(
  user: User
){

  lazy val bCryptPasswordEncoder: BCryptPasswordEncoder = new BCryptPasswordEncoder

  case class Login(id: java.lang.Long, token: String, timestamp: Timestamp)

  def login(email: String, password: String): loginResult = {
    val user: User = user.whe.getOrElse(
      return new loginResult("userNotFound",null, "")
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
