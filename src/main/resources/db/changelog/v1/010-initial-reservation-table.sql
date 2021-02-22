create type reservation_enum as enum ('CONFIRMED', 'CANCELED');

create table reservation
(
    id bigserial unique,
    application_date date,
    arrival_date date,
    departure_date date,
    status reservation_enum default 'CONFIRMED',
    room_id int,
    primary key(id),
    constraint fk_reservation_room foreign key (room_id) references room (id)
);