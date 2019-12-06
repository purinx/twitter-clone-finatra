package com.kijimaru.twitter.domain.dto

case class UserForm(
  id: Option[Long],
  rawPassword: String,
  name: String,
  email: String,
  token: Option[String]
) {
  def patchPassword(passwordInput: String): UserForm = copy(rawPassword=passwordInput)
  def patchName(nameInput: String): UserForm = copy(name=nameInput)
  def patchEmail(emailInput: String): UserForm = copy(email=emailInput)
  def patchToken(tokenInput: String): UserForm = copy(token=Option(tokenInput))
}
