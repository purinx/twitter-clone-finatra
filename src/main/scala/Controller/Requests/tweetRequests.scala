package Controller.Requests

import com.twitter.finatra.request.{JsonIgnoreBody, QueryParam, RouteParam}

@JsonIgnoreBody
case class LikeRequest(@RouteParam tweetId:Long, @QueryParam userId:Long)


