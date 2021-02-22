create table room
(
    id bigserial unique,
    category_id int,
    primary key(id),
    constraint fk_room_category foreign key (category_id) references categories (id)
);