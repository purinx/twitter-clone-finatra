package com.kijimaru.twitter.domain.repository

import com.kijimaru.twitter.domain.entity.User

trait UserRepository {

  def save(form: User): Either[String, Long]

  def delete(): Either[String, Long]

  def findByEmail(): Either[String, User]

}
