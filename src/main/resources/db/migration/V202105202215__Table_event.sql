create table event
(
    x           integer not null,
    y           integer not null,
    constraint events_pk
        primary key (x, y),
    type        varchar not null,
    constraint type_check
        check ( type in ('ADVENTURE', 'TREASURE', 'BOTTLE') ),
    title       varchar not null,
    description varchar not null,
    next_action varchar,
    constraint next_action_check
        check ( next_action in ('QUESTION', 'DICE', 'NEXT_MOVE', 'VISION', 'SICK') ),
--     conjunction varchar not null default 'AND',
--         constraint conjunction_check
--             check ( conjunction in ('AND', 'OR') ),
--     reward boolean not null default true,
    removable   boolean not null
);

create table event_in_game
(
    x           integer      not null,
    constraint x_check
        check ( x > 0 AND x < 9 ),
    y           integer      not null,
    constraint y_check
        check ( y > 0 AND y < 10 ),
    game        varchar(255) not null,
    constraint events_in_game_pk
        primary key (x, y, game),
    type        varchar      not null,
    constraint type_check
        check ( type in ('ADVENTURE', 'TREASURE', 'BOTTLE') ),
    title       varchar      not null,
    description varchar      not null,
    next_action varchar,
    constraint next_action_check
        check ( next_action in ('QUESTION', 'DICE', 'NEXT_MOVE', 'VISION', 'SICK') ),
--     conjunction varchar not null default 'AND',
--         constraint conjunction_check
--             check ( conjunction in ('AND', 'OR') ),
--     reward boolean not null default true,
    removable   boolean      not null
);

create table event_reward
(
    id uuid    not null
        constraint event_reward_pk
            primary key,
    x  integer not null,
    constraint x_check
        check ( x > 0 AND x < 9 ),
    y  integer not null,
    constraint y_check
        check ( y > 0 AND y < 10 )
);
