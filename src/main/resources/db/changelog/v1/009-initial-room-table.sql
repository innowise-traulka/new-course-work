create table room
(
    id bigserial unique,
    number int,
    category_id int,
    primary key(id),
    constraint fk_room_category foreign key (category_id) references categories (id)
);