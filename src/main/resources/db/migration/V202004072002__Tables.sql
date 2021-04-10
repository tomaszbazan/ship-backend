create table game
(
    id uuid not null,
    name varchar not null,
    password varchar not null,
    start_date date not null
--     finished bool default false not null
);

create unique index game_id_uindex
    on game (id);

create unique index game_name_uindex
    on game (name);

alter table game
    add constraint game_pk
        primary key (id);

