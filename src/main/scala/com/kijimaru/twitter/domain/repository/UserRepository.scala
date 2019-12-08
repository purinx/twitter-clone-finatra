package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.dto.UserForm
import com.kijimaru.twitter.domain.entity.User
import scala.util.Try

trait UserRepository {

  import UserRepository._

  def create(form: CreateUserRequest): Try[Long]

  def findById(userId: Long): Option[User]

  def findByEmail(email: String): Option[User]

  def findByToken(token: String): Option[User]

  def update(form: UserForm): Either[String, Boolean]

  def authenticate(email: String, rawPassword: String): Either[String, Boolean]

  def getAll(): List[User]

  def setToken(userId: Long, token: String): Unit

}

object UserRepository {

  case class CreateUserRequest(
    id: String,
    email: String,
    hashedPassword: String
  )

}
