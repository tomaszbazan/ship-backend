create table player_in_game
(
    id uuid not null
        constraint player_in_game_pk
            primary key,
    player_id uuid not null
        constraint player_in_game_player_fk
            references player,
    game_id uuid not null
        constraint player_in_game_game_fk
            references game,
    country varchar(255) not null,
        constraint country_check
            check ( country in ( 'JAMAICA', 'HAITI', 'CUBA', 'GUATEMALA', 'PUERTO_RICO') ),
    position_on_board_x int,
        constraint x_check
            check ( position_on_board_x > 0 AND position_on_board_x < 9 ),
    position_on_board_y int,
        constraint y_check
            check ( position_on_board_y > 0 AND position_on_board_y < 10 ),
    occurrence timestamp not null default now()
);
