create table game
(
    id uuid not null
        constraint game_pk
            primary key,
    name varchar not null unique,
    password varchar not null,
    start_date date not null
);
