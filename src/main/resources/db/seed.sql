insert into user (name, email, password, token) values
('no1', 'sample1', '2009-07-26@00:00:00', CURRENT_TIMESTAMP),
('no2', 'sample2', '2010-07-26@00:00:00', CURRENT_TIMESTAMP),
('no3', 'sample3', '2011-07-26@00:00:00', CURRENT_TIMESTAMP),
('no4', 'sample4', '2012-07-26@00:00:00', CURRENT_TIMESTAMP),
('no5', 'sample5', '2013-07-26@00:00:00', CURRENT_TIMESTAMP),
('no6', 'sample6', '2014-07-26@00:00:00', CURRENT_TIMESTAMP),
('no7', 'sample7', '2015-07-26@00:00:00', CURRENT_TIMESTAMP),
('no8', 'sample8', '2016-07-26@00:00:00', CURRENT_TIMESTAMP),
('no9', 'sample9', '2017-07-26@00:00:00', CURRENT_TIMESTAMP),
('no10', 'sample10', '2082-07-26@00:00:00', CURRENT_TIMESTAMP);

-- userの件数が膨大になる前に1000件followをinsertしておく
insert into follow(user_id, followed)
    select ceil(rand() * 1000), ceil(rand() * 1000)
    from user A , user B, user C, user D;

insert into retweet(user_id, tweet_id)
    select ceil(rand() * 1000), ceil(rand() * 100000)
    from user A , user B, user C, user D;

insert into user (name, email, password, token)
    select substring(md5(rand()), 1, 20), substring(md5(rand()), 1, 20), s1.password, s1.token
    from user s1, user s2, user s3;


drop table if exists user2;

insert into tweet (user_id, user_name, text, content, liked, retweeted, timestamp) values
(1, 'csdgrthw', substring(md5(rand()),0,100),null, 0,4,current_timestamp),
(2, 'vfdbjkjbsda', substring(md5(rand()), 0, 100), null,1,5, CURRENT_TIMESTAMP),
(3, 'xcasdfeabbag', substring(md5(rand()), 0, 100), null,1,3, CURRENT_TIMESTAMP),
(4, 'opjkhgfdsfsvewhy', substring(md5(rand()), 0, 100), null,1,3, CURRENT_TIMESTAMP),
(5, 'saoikjrepok', substring(md5(rand()), 0, 100), null,3, 8,CURRENT_TIMESTAMP),
(6, 'csdgrthw', substring(md5(rand()), 0, 100), null, 0,4,current_timestamp),
(7, 'vfdbjkjbsda', substring(md5(rand()), 0, 100), null,1,5, CURRENT_TIMESTAMP),
(8, 'xcasdfeabbag', substring(md5(rand()), 0, 100), null,1,3, CURRENT_TIMESTAMP),
(9, 'opjkhgfdsfsvewhy', substring(md5(rand()), 0, 100), null,1,3, CURRENT_TIMESTAMP),
(10, 'saoikjrepok', substring(md5(rand()), 0, 100),  null,3, 8,CURRENT_TIMESTAMP);

-- 10 ^ 8 件のデータが作成できる
insert into tweet (user_id, `text`, content, liked, retweeted, `timestamp`)
 select ceil(rand() * 1000), substring(md5(rand()),0,100), null, ceil(rand() * 1000), ceil(rand() * 1000), s1.`timestamp`
 from tweet s1, tweet s2, tweet s3, tweet s4, tweet s5, tweet s6, tweet s7;


-- このままだと同じデータが繰り返しinsertさせただけなので
-- ランダムな値に update する


