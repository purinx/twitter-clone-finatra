package com.kijimaru.twitter.domain.entity

case class User(
    id: Long,
    password: String,
    screenName: String,
    email: String,
    token: String
)
