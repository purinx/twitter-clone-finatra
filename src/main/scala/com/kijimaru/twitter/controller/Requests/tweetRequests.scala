package com.kijimaru.twitter.controller.Requests

import com.twitter.finatra.request.{JsonIgnoreBody, QueryParam, RouteParam}

@JsonIgnoreBody
case class LikeRequest(@RouteParam tweetId:Long, @QueryParam userId:Long)


