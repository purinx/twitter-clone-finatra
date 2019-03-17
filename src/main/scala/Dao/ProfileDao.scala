package Dao

import Model.Profile
import io.getquill._

class ProfileDao {

  lazy val ctx: MysqlJdbcContext[SnakeCase.type] = new MysqlJdbcContext(SnakeCase, "ctx")

  import ctx._

  def create(userId: Long, called: String, bio: String, icon: String) = {
    val q = quote {
      query[Profile].insert(
        _.userId -> lift(userId),
        _.called -> lift(called),
        _.bio -> lift(bio),
        _.icon -> lift(icon)
      )
    }
    run(q)
  }

  def update(userId: Long, called: String, bio: String, icon: String) = {
    val q = quote {
      query[Profile].filter(_.userId == lift(userId))
        .update(_.called -> lift(called), _.bio -> lift(bio), _.icon -> lift(icon))
    }
    run(q)
  }

  def findById(userId: Long):Option[Profile] = {
    val q = quote {
      query[Profile].filter(_.userId==lift(userId)).take(1)
    }
    run(q).headOption
  }
}
