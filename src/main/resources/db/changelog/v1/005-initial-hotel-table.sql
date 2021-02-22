create table hotel
(
    id bigserial unique,
    name varchar(100),
    address_id int,
    primary key(id)
);