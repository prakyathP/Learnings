insert into user_details(id, birth_date, name)
values(10001, current_date(), 'Ranga');
insert into user_details(id, birth_date, name)
values(10002, current_date(), 'Ravi');
insert into user_details(id, birth_date, name)
values(10003, current_date(), 'Sathish');
--create table post (id integer not null, description varchar(255), user_id integer, primary key (id))
--create table user_details (id integer not null, birth_date date, name varchar(255), primary key (id))
insert into post(id,description,user_id)
values(20001, 'I want to learn AWS', 10001);
insert into post(id, description, user_id)
values(20002, 'I want to learn DevOps', 10001);
insert into post(id, description, user_id)
values(20003, 'Praj will learn GCP', 10002);
insert into post(id, description, user_id)
values(20004, 'Praj will learn MP', 10002)