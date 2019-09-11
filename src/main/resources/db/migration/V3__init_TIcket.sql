create sequence ticket_sequence start 1 increment 1;
create table ticket (
	id int8 not null default nextval('ticket_sequence'),
  users_id int8,
  event_date date,
  cost decimal ,
  primary key (id)
);

INSERT into ticket (users_id, event_date, cost)
VALUES(1, '2018-09-11', 14.99);

INSERT into ticket (users_id, event_date, cost)
VALUES(2, '2019-09-06', 14.99);

