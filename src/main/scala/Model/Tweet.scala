package Model

case class Tweet(
                  id: Long,
                  userId: Long,
                  userName: String,
                  userSubname: String,
                  userIcon: String,
                  text: String,
                  content: String,
                  liked: Long,
                  retweeted: Long,
                  timestamp: String
                )
