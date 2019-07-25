package Model

case class Profile(id: Long, userId: Long, subName: String,
                   bio: String, icon: String, privacy: String,
                   tweets: Long, likes: Long, following: Long, followed: Long)
