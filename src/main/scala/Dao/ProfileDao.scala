package Dao

import Model.Profile
import io.getquill._

class ProfileDao {

  lazy val ctx: MysqlJdbcContext[SnakeCase.type] = new MysqlJdbcContext(SnakeCase, "ctx")

  import ctx._

  def create(userId: Long, subName: String, bio: String, icon: String) = {
    val q = quote {
      query[Profile].insert(
        _.userId -> lift(userId),
        _.subName -> lift(subName),
        _.bio -> lift(bio),
        _.icon -> lift(icon)
      )
    }
    run(q)
  }

  def update(userId: Long, subName: String, bio: String, icon: String) = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId))
        .update(
          _.subName -> lift(subName),
          _.bio -> lift(bio),
          _.icon -> lift(icon)
        )
    }
    run(q)
  }

  def findById(userId: Long): Option[Profile] = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId)).take(1)
    }
    run(q).headOption
  }
}
