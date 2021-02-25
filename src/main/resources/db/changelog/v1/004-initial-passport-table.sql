create table passport
(
    id int primary key references users (id),
    number int,
    serial varchar(2),
    issuedAt date
);