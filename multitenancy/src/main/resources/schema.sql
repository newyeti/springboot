create schema ds1;
create schema ds2;

create table ds1.person
(
    id          integer primary key,
    first_name  varchar(64)  not null,
    last_name   varchar(64)      not null

);

create table ds2.person
(
    id          integer primary key,
    first_name  varchar(64)  not null,
    last_name   varchar(64)      not null

);



create  sequence  ds1.person_seq;
create  sequence  ds2.person_seq;