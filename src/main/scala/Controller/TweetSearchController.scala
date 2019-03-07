package Controller

import Dao.TweetDao
import io.getquill.{MysqlJdbcContext, SnakeCase}
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.QueryParam
import com.twitter.util.Future

class TweetSearchController extends Controller {

  lazy val ctx = new MysqlJdbcContext(SnakeCase, "ctx")
  val tweetDao: TweetDao = new TweetDao(ctx)

  case class TweetsRequest(@QueryParam from: Int,
                           @QueryParam to: Int)
  get("/tweets") { request: TweetsRequest =>
    Future {
      (request.from to request.to)
        .map(i => tweetDao.findById(i).get)
    }
  }
}