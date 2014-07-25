CREATE DATABASE `spring` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

create user 'spring'@'%' identified by 'drive123';
grant select,update,delete,insert,create,drop,alter on spring.* to 'spring'@'%';
flush privileges;
