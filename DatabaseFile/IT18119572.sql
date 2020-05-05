
create database User;
use user;

create table user(
UserNic varchar(15) not null primary key,
UserName varchar(100),
UserGender varchar(50),
UserContact varchar(20),
UserEmail varchar(50),
UserPassword varchar(20) 
);


insert  into user values('123658752', 'kusal', 'Male', '0718381557', 'kusal@gmail.com', 'Kusal123456');
insert  into user values('18119572', 'Chaturanga', 'Male', '0724475890', 'chaturanga@gmail.com', 'chaturanga123456');


