package com.kijimaru.twitter.domain.dto

import com.github.t3hnar.bcrypt._

case class UserForm(
  id: Option[Long],
  rawPassword: String,
  name: String,
  email: String,
) {
  def patchPassword(passwordInput: String): UserForm = copy(rawPassword=passwordInput)
  def patchName(nameInput: String): UserForm = copy(name=nameInput)
  def patchEmail(emailInput: String): UserForm = copy(email=emailInput)
  def hashedPassword: String = rawPassword.bcrypt
}
