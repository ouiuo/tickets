
create sequence users_sequence start 1 increment 1;
create table users (
  id int8 not null default nextval('users_sequence'),
  login varchar(255) unique,
  password varchar(255),
  name varchar(250),
  second_name varchar(255),
  third_name varchar(255),
  gender_id int8,
  role_id int8,
  primary key (id)
);


INSERT into users(login, password, name, second_name, third_name, gender_id, role_id)
VALUES ('admin', '$2a$10$Fz5ad8MtcAwU/AcbG4DJo.oIGZQR84gdB21crEDWgz.0nLCakYxwm', 'vasya', 'degtyaryov', 'Alecksandrivich', 1, 1);


INSERT into users(login, password, name, second_name, third_name, gender_id, role_id)
VALUES ('users', '$2a$10$Fz5ad8MtcAwU/AcbG4DJo.oIGZQR84gdB21crEDWgz.0nLCakYxwm', 'vova', 'psin', 'cosin', 2, 2);