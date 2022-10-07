create table songs
(
    id          int auto_increment
        primary key,
    name        varchar,
    artist      varchar,
    album       varchar,
    length      varchar,
    year        varchar,
    resource_id int
);