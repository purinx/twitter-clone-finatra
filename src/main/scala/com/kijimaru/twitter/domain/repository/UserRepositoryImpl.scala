package com.kijimaru.twitter.domain.repository

import javax.inject.Inject
import com.kijimaru.twitter.domain.entity.User
import com.kijimaru.twitter.module.DBModule.DBContext

import scala.util.Try

class UserRepositoryImpl @Inject()(
  ctx: DBContext
) extends UserRepository {

  import UserRepository._
  import ctx._

  override def create(request: CreateUserRequest): Try[Long] = Try {
    run(
      quote {
        query[User]
          .insert(
            _.screenName -> request.screenName,
            _.email -> request.email,
            _.password -> request.hashedPassword
          )
          .returning(_.id)
      }
    )
  }

  override def findById(id: Long): Option[User] = run {
    quote {
      querySchema[User]("users")
        .filter(_.id == lift(id))
    }
  }.headOption

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

  override def update(request: UpdateUserRequest): Try[Unit] = Try {
    run(
      quote {
        query[User]
          .filter(_.id == lift(request.id))
          .update(
            _.screenName -> request.screenName,
            _.email -> request.email,
            _.password -> request.hashedPassword,
          )
      }
    )
  }
}
