create table card
(
    id          uuid    not null
        constraint card_pk
            primary key,
    type        varchar not null,
        constraint type_check
            check ( type in ('SNAIL', 'BILL_OF_EXCHANGE', 'RUM', 'SICK', 'CURE', 'EXCHANGE', 'DEPOSIT', 'SHOPPING_LIST') ),
    conjunction varchar not null default 'AND',
        constraint conjunction_check
            check ( conjunction in ('AND', 'OR') ),
    event_reward_id uuid not null
        constraint event_reward_fk
            references event_reward,
    player_in_game_id    uuid
        constraint player_in_game_fk
            references player_in_game
);
