package com.kijimaru.twitter.servise

import javax.inject.Inject
import com.kijimaru.twitter.domain.entity.User
import com.kijimaru.twitter.domain.repository.UserRepository
import com.github.t3hnar.bcrypt._
import java.sql.Timestamp // FIXME

class UserService @Inject()(
  userRepository: UserRepository
) {

  import UserService._

  def login(email: String, password: String): Either[String, Login] = {
    for {
      user <- userRepository.findByEmail(email).toRight("userNotFound")
      _ <- Either.cond(password.bcrypt == user.password, (), "invalid password") // FIXME: seed欲しい
    } yield {
      //100文字のランダムな文字列
      val token = scala.util.Random.alphanumeric.take(100).mkString
      userRepository.setToken(user.id, token)
      Login(user.id, token, new Timestamp(System.currentTimeMillis()))
    }
  }

}

object UserService {

  case class Login(id: Long, token: String, timestamp: Timestamp)

}
