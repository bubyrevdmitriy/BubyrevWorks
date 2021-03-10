create sequence hibernate_sequence start 1 increment 1;

create table author (
    id int8 not null,
    filename varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    middle_name varchar(255),
    tag varchar(255),
    primary key (id)
    );

create table book (
    id int8 not null,
    country varchar(255),
    filename varchar(255),
    name varchar(255),
    tag varchar(255),
    year varchar(255),
    author_id int8,
    primary key (id)
    );

create table order_role (
    order_id int8 not null,
    orderroles varchar(255)
    );

create table ordr (
    id int8 not null,
    start_time varchar(255),
    stock_time int8,
    book_id int8,
    user_id int8,
    primary key (id)
    );

create table user_role (
    name_id int8 not null,
    roles varchar(255)
    );

create table usr (
    id int8 not null,
    activation_code_email varchar(255),
    activation_code_phone varchar(255),
    active boolean not null,
    email varchar(255),
    filename varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    middle_name varchar(255),
    password varchar(255) not null,
    phone varchar(255),
    username varchar(255) not null,
    primary key (id)
    );

alter table if exists book
    add constraint book_to_author_fk
        foreign key (author_id) references author;

alter table if exists order_role
    add constraint order_role_to_order_fk
        foreign key (order_id) references ordr;

alter table if exists ordr
    add constraint order_to_book_fk
        foreign key (book_id) references book;

alter table if exists ordr
    add constraint order_to_user_fk
        foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_to_user_fk
    foreign key (name_id) references usr;