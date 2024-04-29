--liquibase formatted sql

--changeset muslimov_vlad:1
create sequence if not exists user_seq start with 3 increment by 1;

create table if not exists users (
                                     id bigint not null,
                                     email varchar(255) not null unique,
                                     username varchar(255) not null unique,
                                     password varchar(255) not null,
                                     role varchar(255) not null check (role in ('CLIENT','ADMIN')),
                                     primary key (id)
    );
