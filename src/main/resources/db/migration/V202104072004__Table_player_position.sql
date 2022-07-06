create table player_position
(
    id         uuid         not null
        constraint player_position_pk
            primary key,
    player     varchar(255) not null,
    game       varchar(255) not null,
    country    varchar(255) not null,
    constraint country_check
        check ( country in ('JAMAICA', 'HAITI', 'CUBA', 'GUATEMALA', 'PUERTO_RICO') ),
    x          int,
    constraint x_check
        check ( x > 0 AND x < 9 ),
    y          int,
    constraint y_check
        check ( y > 0 AND y < 10 ),
    occurrence timestamp    not null default now(),
    action     varchar(20)  not null,
    constraint action_check
        check ( action in ('JOIN', 'MOVE', 'BOTTLE', 'BOTTLE_ACCEPTED', 'BOTTLE_DECLINED', 'ADVENTURE', 'ADVENTURE_ACCEPTED', 'TREASURE', 'TREASURE_ACCEPTED', 'TREASURE_DECLINED') ),
    round      varchar(20)  not null,
    constraint round_check
        check ( round in ('PREPARING', 'ROUND_1_MOVE', 'ROUND_2_MOVE', 'ROUND_3_MOVE', 'ROUND_4_MOVE',
                           'ROUND_5_MOVE', 'ROUND_6_MOVE', 'ROUND_7_MOVE', 'ROUND_8_MOVE', 'ROUND_9_MOVE',
                           'ROUND_10_MOVE', 'ROUND_11_MOVE', 'ROUND_12_MOVE', 'FINISHED') )
);
