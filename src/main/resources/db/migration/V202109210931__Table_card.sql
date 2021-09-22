create table card
(
    id          uuid    not null
        constraint card_pk
            primary key,
    type        varchar not null,
        constraint type_check
            check ( type in ('') ),
    conjunction varchar not null,
        constraint conjunction_check
            check ( conjunction in ('AND', 'OR') )
);

create table card_goods
(
    id          uuid    not null
        constraint card_goods_pk
            primary key,
    card_id uuid not null
        constraint card_goods_card_fk
            references card,
    goods_id uuid not null
        constraint card_goods_goods_fk
            references goods
);

create table player_in_game_card
(
    id          uuid    not null
        constraint player_in_game_card_pk
            primary key,
    player_in_game_id uuid not null
        constraint player_in_game_card_player_in_game_fk
            references player_in_game,
    card_id uuid not null
        constraint player_in_game_card_card_fk
            references card
);
