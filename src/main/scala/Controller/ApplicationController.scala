package Controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

class ApplicationController extends Controller {

  get("/:*") { request: Request =>
    response.ok.fileOrIndex(
      request.params("*"),
      "index.html")
  }

  get("/app") { request: Request =>
    response.ok.file("index.html")
  }

  get("/login") { request: Request =>
    response.ok.file("login.html")
  }

  get("/signup") { request: Request =>
    response.ok.file("signup.html")
  }
}
