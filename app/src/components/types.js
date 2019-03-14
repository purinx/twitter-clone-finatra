export type TweetType = {
  id: number,
  userIcon: string,
  username: string,
  userCalled: string,
  userId: string,
  text: string,
  liked: number,
  retweeted: number,
  timestamp: number,
}

export type User = {
  id: number,
  name: string,
  token: string,
}

export type Profile = {
  userId: number,
  called: string,
  bio: string,
  icon: string,
  privacy: Boolean,
  tweets: number,
  likes: number,
  following: number,
  followed: number,
}
