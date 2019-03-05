package Controller

import Dao.TweetDao
import io.getquill.{MysqlJdbcContext, SnakeCase}
import com.twitter.finatra.http.Controller
import com.twitter.finagle.Http
import com.twitter.finagle.http.Request

class TweetSearchController extends Controller{

  lazy val ctx = new MysqlJdbcContext(SnakeCase, "ctx")
  val tweetDao:TweetDao = new TweetDao(ctx)

  get("/tweet") {request:Request =>
    "実装中"
  }
}