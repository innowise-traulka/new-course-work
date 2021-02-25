create table users
(
    id bigserial unique,
    first_name varchar(50),
    last_name  varchar(50),
    patronymic varchar(50),
    email varchar(100),
    password varchar(255),
    status status_enum default 'ACTIVE',
    role_id int,
    primary key (id),
    constraint fk_user_roles foreign key (role_id) references roles (id)
);