/// @flow

import {tweet, deleteTweet, timeline, like, retweet} from '../src/api/tweetAPI';

const TL = timeline(1);
console.log(TL);

/*
* like qpi
* @param tweetId  number
* @param userId   number
* return ??
* */
//const result1 = like(1,1);
//console.log(result1);