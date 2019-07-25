package Dao

import Model.Follow
import Model.User
import Module.DBModule
import Module.DBModule.DBContext
import io.getquill.{MysqlJdbcContext, SnakeCase}
import javax.inject.Inject

class FollowDao @Inject()(
  ctx: DBContext,
  userDao: UserDao) {

  import ctx._

  def getFollowingIdList(userId: Long): List[Long] = {
    val followQ = quote {
      query[Follow].withFilter(_.userId == lift(userId)).map(_.followed)
    }
    run(followQ)
  }

  def getFollowingUserList(userId: Long): List[Option[User]] = {
    val idList = getFollowingIdList(userId)
    idList.map(i => userDao.findById(i))
  }
}
