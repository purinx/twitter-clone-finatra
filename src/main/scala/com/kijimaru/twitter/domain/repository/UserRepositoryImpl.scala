package com.kijimaru.twitter.domain.repository

import com.google.inject.Inject
import com.kijimaru.twitter.domain.dto.UserForm
import com.kijimaru.twitter.domain.entity.User
import com.kijimaru.twitter.module.DBModule.DBContext

import scala.util.{Failure, Success, Try}

class UserRepositoryImpl @Inject()(ctx: DBContext) extends UserRepository {

  import ctx._

  override def create(form: UserForm): Try[Long] = Try {
    run(
      quote {
        query[User]
          .insert(
            _.screenName -> form.screenName,
            _.email -> form.email,
            _.password -> form.hashedPassword
          )
          .returning(_.id)
      }
    )
  }

  override def authenticate(email: String, rawPassword: String): Either[String, Boolean] = {
    import com.github.t3hnar.bcrypt._
    val passwordQueryResult: Option[String] = run(
      quote {
        query[User]
          .filter(_.email == lift(email))
          .take(1)
          .map(_.password)
      }
    ).headOption
    passwordQueryResult match {
      case None => Left("User not found.")
      case Some(hash) => Right(rawPassword.isBcrypted(hash))
    }
  }

  override def findByEmail(email: String): Option[User] = {
    run(
      quote {
        query[User].filter(_.email == lift(email))
      }
    ).headOption
  }

  override def findByToken(token: String): Option[User] = {
    run(
      quote {
        query[User].filter(_.token == lift(token))
      }
    ).headOption
  }

  override def update(form: UserForm): Either[String, Boolean] = form.id match {
    case None => Left("new user")
    case Some(id) =>
      val result = Try(
        run(
          quote {
            query[User]
              .filter(_.id == lift(id))
              .update(
                _.screenName -> form.screenName,
                _.email -> form.email,
                _.password -> form.hashedPassword,
              )
          }
        )
      )
      result match {
        case Success(_) => Right(true)
        case Failure(e) => Left(e.toString)
      }
  }
}
