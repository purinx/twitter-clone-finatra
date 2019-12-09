drop table if exists tweet;
drop table if exists user;
drop table if exists profile;
drop table if exists follow;
drop table if exists likes;
drop table if exists retweet;

create table tweet(
  `id` int not null auto_increment,
  `user_id` bigint not null,
  `user_name` varchar(255),
  `user_subname` varchar(255),
  `user_icon` varchar(255),
  `text` varchar(255),
  `content` varchar(255) null,
  `liked` int default 0,
  `retweeted` int default 0,
  `timestamp` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(`id`)
);

create table user(
  `id` int not null auto_increment,
  `password` varchar(255),
  `name` varchar (255) unique,
  `email` varchar(255) unique,
  `token` varchar (255),
  primary key(id)
);

CREATE TABLE profile (
  `user_id` int NOT NULL UNIQUE,
  `subname` VARCHAR(255) not null,
  `bio` varchar(255),
  `icon` VARCHAR(255),
  `privacy` VARCHAR(45) default 'public',
  `tweets` int default 0,
  `likes` int default 0,
  `following` int default 0,
  `followed` int default 0,
  PRIMARY KEY (user_id)
);

create table follow(
  `id` int auto_increment,
  `user_id` int not null,
  `followed` int not null,
  `backed` bool,
  primary key(id)
);

create table `likes`(
  `id` int auto_increment,
  `tweet_id` int not null,
  `user_id` int not null,
  primary key(id)
);

create table retweet(
  `id` int auto_increment,
  `user_id` int not null,
  `tweet_id` int not null,
  `timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  primary key(id)
);
