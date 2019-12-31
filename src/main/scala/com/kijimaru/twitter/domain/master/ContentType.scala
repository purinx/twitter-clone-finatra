package com.kijimaru.twitter.domain.master

sealed abstract class ContentType(id: Int) {
  def getValue: Int = id
}

object ContentType {

  case object Empty extends ContentType(1)

  case object Image extends ContentType(2)

  case object Video extends ContentType(3)

  case object Url extends ContentType(4)

  def fromId(id: Long): ContentType = id match {
    case 1 => Empty
    case 2 => Image
    case 3 => Video
    case 4 => Url
  }
}
