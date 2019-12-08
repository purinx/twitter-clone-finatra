package com.kijimaru.twitter.controller

import com.kijimaru.twitter.domain.repository.ProfileRepository
import com.kijimaru.twitter.domain.repository.UserRepository
import com.kijimaru.twitter.servise.UserService
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import javax.inject.Inject
import com.github.t3hnar.bcrypt._

class UserManageController @Inject()(
  userRepository: UserRepository,
  profileRepository: ProfileRepository,
  userService: UserService
) extends Controller {


  get("/user/all") { request: Request =>
    userRepository.getAll
  }

  get("/user/:userId/profile/:profileId") { request: Request =>
    val profileId = request.getLongParam("profileId")
    profileRepository.isPublic(profileId) match {
      case None => "profile not found"
      case Some(true) => profileRepository.findById(request.getLongParam("userId"))
      // implement private cehck
      case Some(false) => "private user"
    }
    profileRepository.findById(request.getLongParam("userId"))
  }


  //TODO Filter only user own.
  //TODO Receive icon as img file. Then save url of bucket.
  post("/user/:userId/profile/update") { request: Request =>
    profileRepository.update(
      request.getLongParam("userId"),
      request.getParam("name"),
      request.getParam("bio"),
      request.getParam("icon")
    )
  }

  post("/user/create") { request: Request =>
    import com.kijimaru.twitter.domain.repository.UserRepository.CreateUserRequest

    val passwordHash = request.getParam("password").bcrypt // FIXME: seed欲しい
    val req = CreateUserRequest(
      request.getParam("id"),
      request.getParam("email"),
      passwordHash
    )

    userRepository.create(req)
  }

  post("/user/:userId/profile/create") { request: Request =>
    profileRepository.create(
      request.getParam("userId").toLong,
      request.getParam("name"),
      request.getParam("bio"),
      request.getParam("icon")
    )
  }

  post("/user/login") { request: Request =>
    userService.login(
      request.getParam("id"),
      request.getParam("password")
    )
  }
}
