create table player_cards
(
    id                 uuid    not null
        constraint player_cards_pk
            primary key,
    type               varchar not null
        constraint type_check
            check ( type in ('SNAIL', 'BILL_OF_EXCHANGE', 'RUM', 'CURE', 'EXCHANGE', 'DEPOSIT', 'SHOPPING_LIST', 'JOKER') ),
    amount             integer not null
        constraint amount_check
            check ( amount > 0 ),
    player_position_id uuid
        constraint cards_player_position_fk
            references player_position
);

create table event_card
(
    id          uuid    not null
        constraint card_pk
            primary key,
    type        varchar not null,
        constraint type_check
            check ( type in ('SNAIL', 'BILL_OF_EXCHANGE', 'RUM', 'CURE', 'EXCHANGE', 'DEPOSIT', 'SHOPPING_LIST', 'JOKER') ),
    conjunction varchar not null default 'AND',
        constraint conjunction_check
            check ( conjunction in ('AND', 'OR') ),
    event_reward_id uuid not null
        constraint event_reward_fk
            references event_reward
);
