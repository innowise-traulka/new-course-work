create table user_confirmation_code
(
    id bigint primary key references users (id),
    code varchar(255),
    created_at date
);