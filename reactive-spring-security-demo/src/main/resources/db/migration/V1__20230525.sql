create table users
(
    id         serial primary key,
    username   varchar(255) not null unique,
    password   varchar(255) not null,
    role       varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    status     varchar(255) not null,
    created_at timestamp,
    updated_at timestamp
)