CREATE TABLE auto_user
(
    id        serial primary key,
    email     varchar unique not null,
    password  varchar        not null
);