package dao

import Model.{Follow, Tweet, User}
import Module.DBModule.DBContext
import io.getquill.MysqlJdbcContext

class FollowDaoImpl extends FollowDao {

  override def findFollowingUsersByUserId(userId: Long): F[List[User]] = {
    ctx: DBContext =>
      import ctx._
      val q = quote {
        for {
          following <- query[Follow].withFilter(_.userId == lift(userId))
          user <- query[User].join(_.id == following.followed)
        } yield user
      }
      run(q)
  }

  override def getFollowingIdList(userId: Long): F[List[Long]] = {
    ctx: DBContext =>
      import ctx._
      val q = quote {
        query[Follow].withFilter(_.userId == lift(userId)).map(_.userId)
      }
      run(q)
  }
}