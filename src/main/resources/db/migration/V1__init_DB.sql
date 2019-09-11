create sequence hibernate_sequence start 1 increment 1;
create sequence role_sequence start 1 increment 1;
create sequence gender_sequence start 1 increment 1;

create table role (
  id int8 not null default nextval('role_sequence'),
  name varchar(255),
  primary key (id)
);


create table gender (
	id int8 not null default nextval('gender_sequence'),
  name varchar(255),
  primary key(id)
);


INSERT into role(id, name)
VALUES (1, 'ADMIN');

INSERT into role(id, name)
values (2, 'USERS');




INSERT into gender(id, name)
VALUES (1, 'MAN');

INSERT into gender(id, name)
values (2, 'WOMAN');

INSERT into gender(id, name)
values (3, 'UNDEFINED');




