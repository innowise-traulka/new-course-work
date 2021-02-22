create table address
(
    id int primary key references hotel (id),
    country varchar(50),
    town varchar(50),
    street varchar(50),
    house_number int
);