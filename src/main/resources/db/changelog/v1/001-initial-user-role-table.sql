create table roles
(
    id bigserial unique,
    name varchar(50) default 'USER',
    primary key (id)
);