package dao

import Model.User
import Module.DBModule
import Module.DBModule.DBContext
import io.getquill.{MysqlJdbcContext, SnakeCase}
import javax.inject.Inject

class UserDao @Inject()(ctx: DBContext) {

  import ctx._

  // 値をフォーマットしたい時こうする
  // Tell quill how to handle Instants
  //implicit val encodeInstant = MappedEncoding[Instant, Date](time => Date.from(time))
  //implicit val decodeInstant = MappedEncoding[Date, Instant](time => time.toInstant)

  // Tell quill how to handle Fuel
  //implicit val encodeFuel = MappedEncoding[Fuel, String](_.name)
  //implicit val decodeFuel = MappedEncoding[String, Fuel] {
  //  case Gasoline.name => Gasoline
  //  case Diesel.name => Diesel
  //}

  def findById(id: Long): Option[User] = {
    val q = quote {
      query[User].filter(_.id == lift(id)).take(1)
    }
    run(q).headOption
  }

  def create(name: String, email: String, password: String): Long = {
    val q = quote {
      query[User].insert(
        _.name -> lift(name),
        _.email -> lift(email),
        _.password -> lift(password),
      )
    }
    run(q)
  }

  def setToken(id: Long, token: String): Unit = {
    val setTokenQuery = quote {
      query[User].filter(_.id == lift(id)).update(_.token -> lift(token))
    }
    run(setTokenQuery)
  }

  def getAll: List[User] = {
    val allQ = quote(query[User].sortBy(_.id))
    run(allQ)
  }

  def findByName(name: String): Option[User] = {
    run(
      quote {
        query[User].filter(_.name == lift(name)).take(1)
      }
    ).headOption
  }
}
