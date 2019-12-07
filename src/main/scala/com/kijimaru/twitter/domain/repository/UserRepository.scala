package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.dto.UserForm
import com.kijimaru.twitter.domain.entity.User
import scala.util.Try

trait UserRepository {

  def create(form: UserForm): Try[Long]

  def findByEmail(email: String): Option[User]

  def findByToken(tokane: String): Option[User]

  def update(form: UserForm): Either[String, Boolean]

  def authenticate(email: String, rawPassword: String): Either[String, Boolean]
}
