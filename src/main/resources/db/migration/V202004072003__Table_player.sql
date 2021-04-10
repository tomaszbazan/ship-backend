create table player
(
    id uuid not null,
    name varchar not null,
    password varchar not null
);

create unique index player_id_uindex
    on player (id);

create unique index player_name_uindex
    on player (name);

alter table player
    add constraint player_pk
        primary key (id);
