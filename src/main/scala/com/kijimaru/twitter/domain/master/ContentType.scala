package com.kijimaru.twitter.domain.master

sealed abstract class ContentType(id: Long) {
  def getValue: Long = id
}

object ContentType {

  case object Empty extends ContentType(1)

  case object Image extends ContentType(2)

  case object Video extends ContentType(3)

  case object Url extends ContentType(4)

}
