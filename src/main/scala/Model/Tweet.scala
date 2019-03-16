package Model

case class Tweet(
                  id: Long,
                  userId: Long,
                  isPrivate: Boolean,
                  text: String,
                  content: String,
                  liked: Long,
                  retweeted: Long,
                  timestamp: String
                )
