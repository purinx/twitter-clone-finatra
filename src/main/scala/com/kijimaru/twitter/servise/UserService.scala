package com.kijimaru.twitter.servise

import com.kijimaru.twitter.domain.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserService {

  lazy val bCryptPasswordEncoder: BCryptPasswordEncoder = new BCryptPasswordEncoder
  lazy val userDao: UserDao = new UserDao

  class loginResult(_status: String, _id:java.lang.Long ,_token: String){
    val status = _status
    val id = _id
    val token = _token
  }

  def login(name: String, password: String): loginResult = {
    val user: User = userDao.findByName(name).getOrElse(
      return new loginResult("userNotFound",null, "")
    )
    val result: Boolean = bCryptPasswordEncoder.matches(password, user.password)
    if (result) {
      //100文字のランダムな文字列
      val token = scala.util.Random.alphanumeric.take(100).mkString
      userDao.setToken(user.id, token)
      return new loginResult("success", user.id, token)
    } else {
      return new loginResult("passwordInvalid", null, "")
    }
  }

}
