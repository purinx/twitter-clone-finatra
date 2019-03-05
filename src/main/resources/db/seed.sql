insert into user (name, email, password, token) values
('no1', 'sample1', '2009-07-26@00:00:00', CURRENT_TIMESTAMP),
('no2', 'sample2', '2010-07-26@00:00:00', CURRENT_TIMESTAMP),
('no3', 'sample3', '2011-07-26@00:00:00', CURRENT_TIMESTAMP),
('no4', 'sample4', '2012-07-26@00:00:00', CURRENT_TIMESTAMP),
('no5', 'sample5', '2013-07-26@00:00:00', CURRENT_TIMESTAMP);
('no6', 'sample6', '2014-07-26@00:00:00', CURRENT_TIMESTAMP),
('no7', 'sample7', '2015-07-26@00:00:00', CURRENT_TIMESTAMP),
('no8', 'sample８', '2016-07-26@00:00:00', CURRENT_TIMESTAMP),
('no9', 'sample９', '2017-07-26@00:00:00', CURRENT_TIMESTAMP),
('no10', 'sample１０', '2082-07-26@00:00:00', CURRENT_TIMESTAMP);


drop table if exists user2;

create table tweet(
  `id` int not null auto_increment,
  `user_id` bigint not null,
  `is_private` bool,
  `text` varchar(255),
  `content` varchar(255) null,
  `liked` int default 0,
  `retweeted` int default 0,
  `timestamp` timestamp,
  PRIMARY KEY(`id`)
);

insert into tweet (user_id ,is_private, text,content, liked,retweeted, timestamp) values
(1, 1,'csdgrthw',null, 0,4,CURRENT_TIMESTAMP),
(2, 0,'vfdbjkjbsda',null,1,5, CURRENT_TIMESTAMP),
(3, 0,'xcasdfeabbag',null,1,3, CURRENT_TIMESTAMP),
(4, 0,'opjkhgfdsfsvewhy',null,1,3, CURRENT_TIMESTAMP),
(5, 0,'saoikjrepok',null,3, 8,CURRENT_TIMESTAMP);

drop table if exists tweet1;
create table tweet1(
  `user_id` bigint not null,
  `is_private` bool,
  `text` varchar(255)
);

drop table if exists tweet2;
create table tweet2(
  `content` varchar(255) null,
  `liked` int default 0,
  `retweeted` int default 0,
  `timestamp` timestamp
);

insert into tweet1(user_id ,is_private, text) values
(1, 1,'csdgrthw'),
(2, 0,'vfdbjkjbsda'),
(3, 0,'xcasdfeabbag'),
(4, 0,'opjkhgfdsfsvewhy'),
(5, 0,'saoikjrepok');

insert into tweet2(content,liked,retweeted,timestamp) values
(null, 0,4,CURRENT_TIMESTAMP),
(null,1,5, CURRENT_TIMESTAMP),
(null,1,3, CURRENT_TIMESTAMP),
(null,1,3, CURRENT_TIMESTAMP),
(null,3, 8,CURRENT_TIMESTAMP);

drop table if exists tweet3;
create table tweet3(
  `content` varchar(255) null,
  `liked` int default 0,
  `retweeted` int default 0,
  `timestamp` timestamp
);

insert into tweet3(content,liked,retweeted,timestamp) values
(null, 0,4,CURRENT_TIMESTAMP),
(null,1,5, CURRENT_TIMESTAMP),
(null,1,3, CURRENT_TIMESTAMP),
(null,1,3, CURRENT_TIMESTAMP),
(null,3, 8,CURRENT_TIMESTAMP);

insert into tweet(user_id,is_private,text,content,liked,retweeted,timestamp)
select A.*, B.* from tweet1 A join tweet2 B;