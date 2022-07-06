create table game
(
    id uuid not null
        constraint game_pk
            primary key,
    game varchar not null unique,
    password varchar not null
);

create table game_state (
    game varchar not null
        constraint game_state_pk
            primary key,
    round varchar not null
);

create table game_configuration (
    game varchar not null
        constraint game_configuration_pk
            primary key,
    round_time integer not null
);
