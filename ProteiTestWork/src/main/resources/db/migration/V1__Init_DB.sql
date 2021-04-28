create sequence hibernate_sequence start 1 increment 1;

create table user_online_time (
    id int8 not null,
    time_left int8,
    user_id int8,
    primary key (id)
    );

create table user_role (
    user_id int8 not null,
    user_roles varchar(255)
    );

create table usr (
    id int8 not null,
    created timestamp,
    status varchar(255),
    updated timestamp,
    email varchar(255),
    password varchar(255),
    phone_number varchar(255),
    user_status varchar(255),
    username varchar(255),
    primary key (id)
    );

alter table if exists user_online_time
    add constraint user_online_time_to_user
        foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_to_user
        foreign key (user_id) references usr;