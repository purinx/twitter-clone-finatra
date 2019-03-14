// @flow
import type { NewTweet } from "./types";

export async function timeline(userId: number) {
  const res = await fetch(`http://localhost:9000/user/${ userId }/timeline`, {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
    },
    mode: 'no-cors'
  });
  const body = await res.json();
  return body;
}

export async function like(tweetId: number, userId: number): Promise<boolean> {
  const res = await fetch(`http://localhost:9000/tweet/${ tweetId }/like`, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
    },
    body: JSON.stringify({ by: userId })
  });
  return await !!res.ok;
}

export async function retweet(tweetId: number, userId: number) {
  const res = await fetch(`http://localhost:9000/tweet/${ tweetId }/retweet`, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
    },
    body: JSON.stringify({ by: userId })
  });
  return await !!res.ok;
}

export async function tweet(tweet: NewTweet): Promise<boolean> {
  const res = await fetch(`http://localhost:9000/tweet/new`, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
    },
    body: JSON.stringify({ tweet: tweet })
  });
  return !!res.ok;
}

export async function deleteTweet(tweetId: number, token: string) {
  const res = await fetch(`http://localhost:9000/tweet/${ tweetId }/delete`, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
    },
    body: JSON.stringify({ token: token })
  });
  return !!res.ok;
}
