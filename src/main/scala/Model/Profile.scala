package Model

case class Profile(userId: Long, called: String, bio: String,
                   icon: String, privacy: String, tweets: Long,
                   likes: Long, following: Long, followed: Long)
