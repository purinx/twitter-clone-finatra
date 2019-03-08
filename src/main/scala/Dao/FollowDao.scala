package Dao

import io.getquill._
import Model.Follow
import Model.User
import Dao.UserDao

class FollowDao {
  lazy val ctx: MysqlJdbcContext[SnakeCase.type] =
    new MysqlJdbcContext(SnakeCase, "ctx")
  import ctx._

  lazy val userDao:UserDao = new UserDao

  def getFollowingIdList(userId:Long):List[Long]={
    val followQ = quote{
      query[Follow].withFilter(_.userId==lift(userId)).map(_.followed)
    }
    ctx.run(followQ)
  }

  def getFollowingUserList(userId:Long):List[Option[User]]={
    val idList=getFollowingIdList(userId)
    idList.map(i=>userDao.findById(i))
  }
}
