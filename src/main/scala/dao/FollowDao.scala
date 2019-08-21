package dao

import Model.Follow
import Model.User
import Module.DBModule
import Module.DBModule.DBContext
import io.getquill.{MysqlJdbcContext, SnakeCase}
import javax.inject.Inject

trait FollowDao {
  type F[A] = DBContext => A

  def getFollowingIdList(userId: Long): F[List[Long]]

  def findFollowingUsersByUserId(userId: Long): F[List[User]]
}
