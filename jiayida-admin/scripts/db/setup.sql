CREATE DATABASE `jiayida` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

create user 'jiayida'@'%' identified by 'group123';
grant select,update,delete,insert,create,drop,index,alter,create view on jiayida.* to 'jiayida'@'%';
flush privileges;
