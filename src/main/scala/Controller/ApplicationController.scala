package Controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

class ApplicationController extends Controller {

  get("/app/:*"){request:Request =>
    response.ok.fileOrIndex(
      "/app/"+request.params("*"),
      "/app/index.html")
  }
}
