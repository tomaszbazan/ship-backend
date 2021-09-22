create table player
(
    id uuid not null
        constraint player_pk
            primary key,
    name varchar not null unique,
    password varchar not null
);
