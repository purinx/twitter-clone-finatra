package com.kijimaru.twitter.module

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import io.getquill.{MysqlJdbcContext, SnakeCase}
import javax.inject.Singleton

object DBModule extends TwitterModule {

  type DBContext = MysqlJdbcContext[SnakeCase.type]
  @Singleton
  @Provides
  def ctx: DBContext = new MysqlJdbcContext(SnakeCase, "ctx")
}
