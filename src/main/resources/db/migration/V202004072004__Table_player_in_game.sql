create table player_in_game
(
    id uuid not null,
    player_id uuid not null
        constraint player_in_game_player_id_fk
            references player,
    game_id uuid not null
        constraint player_in_game_game_id_fk
            references game,
    country varchar(255) not null,
    position_on_board_x int,
    position_on_board_y int
);

create unique index player_in_game_id_uindex
    on player_in_game (id);

alter table player_in_game
    add constraint player_in_game_pk
        primary key (id);

