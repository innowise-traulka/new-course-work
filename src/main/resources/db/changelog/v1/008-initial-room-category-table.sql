create table categories
(
    id bigserial unique,
    name category_enum default 'STANDARD',
    price decimal,
    hotel_id int,
    primary key(id),
    constraint fk_hotel_room_category foreign key (hotel_id) references hotel (id)
);