package com.kijimaru.twitter.infrastructure

import java.io.File

import com.typesafe.config.ConfigFactory

object Config {

  val conf = ConfigFactory.parseFile(new File("../resources/application.properties"))

}
