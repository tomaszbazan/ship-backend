create table player
(
    id uuid not null
        constraint player_pk
            primary key,
    player varchar not null unique,
    password varchar not null
);
