create table car (
    id serial primary key,
    name_car text,
    engine_id int not null unique references engine(id),
    owner_id int not null unique references owners(id)
);