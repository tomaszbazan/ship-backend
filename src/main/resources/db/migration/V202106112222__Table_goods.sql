create table goods_start
(
    id      uuid         not null
        constraint goods_start_pk primary key,
    country varchar(255) not null
        constraint country_check check ( country in ('JAMAICA', 'HAITI', 'CUBA', 'GUATEMALA', 'PUERTO_RICO') ),
    type    varchar      not null
        constraint type_check check ( type in
                                      ('COFFEE', 'COCOA', 'SUGARCANE', 'COTTON', 'TOBACCO', 'TEA', 'VANILLA', 'CITRUS',
                                       'GOLD') ),
    amount  integer      not null
        constraint amount_check check ( amount > 0 )
);

create table goods_end
(
    id      uuid         not null
        constraint goods_end_pk primary key,
    country varchar(255) not null,
    constraint country_check check ( country in ('JAMAICA', 'HAITI', 'CUBA', 'GUATEMALA', 'PUERTO_RICO') ),
    type    varchar      not null
        constraint type_check check ( type in
                                      ('COFFEE', 'COCOA', 'SUGARCANE', 'COTTON', 'TOBACCO', 'TEA', 'VANILLA', 'CITRUS',
                                       'GOLD') ),
    amount  integer      not null
        constraint amount_check check ( amount > 0 )
);

create table player_goods
(
    id                 uuid    not null
        constraint player_goods_pk primary key,
    type               varchar not null
        constraint type_check check ( type in
                                      ('COFFEE', 'COCOA', 'SUGARCANE', 'COTTON', 'TOBACCO', 'TEA', 'VANILLA', 'CITRUS', 'GOLD') ),
    amount             integer not null
        constraint amount_check check ( amount > 0 ),
    player_position_id uuid
        constraint goods_player_position_fk references player_position
);

create table player_deposit_goods
(
    id                 uuid    not null
        constraint player_deposit_goods_pk primary key,
    type               varchar not null
        constraint type_check check ( type in
                                      ('COFFEE', 'COCOA', 'SUGARCANE', 'COTTON', 'TOBACCO', 'TEA', 'VANILLA', 'CITRUS', 'GOLD') ),
    amount             integer not null
        constraint amount_check check ( amount > 0 ),
    player_position_id uuid
        constraint player_deposit_goods_player_position_fk references player_position
);

create table event_goods
(
    id              uuid    not null
        constraint goods_pk primary key,
    type            varchar not null
        constraint type_check check ( type in
                                      ('COFFEE', 'COCOA', 'SUGARCANE', 'COTTON', 'TOBACCO', 'TEA', 'VANILLA', 'CITRUS', 'GOLD') ),
    amount          integer not null
        constraint amount_check check ( amount > 0 ),
    card_id         uuid
        constraint card_fk references event_card,
    event_reward_id uuid
        constraint event_reward_fk references event_reward
);

CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

-- JAMAICA
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'SUGARCANE', 100);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'TOBACCO', 170);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'CITRUS', 80);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'TEA', 280);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'GOLD', 163);

insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'COFFEE', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'COCOA', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'SUGARCANE', 120);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'TOBACCO', 20);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'TEA', 200);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'JAMAICA', 'VANILLA', 70);


-- TODO: HAITI
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'SUGARCANE', 100);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'TOBACCO', 170);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'CITRUS', 80);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'TEA', 280);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'GOLD', 163);

insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'COFFEE', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'COCOA', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'SUGARCANE', 120);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'TOBACCO', 20);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'TEA', 200);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'HAITI', 'VANILLA', 70);

-- TODO: CUBA
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'SUGARCANE', 100);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'TOBACCO', 170);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'CITRUS', 80);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'TEA', 280);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'GOLD', 163);

insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'COFFEE', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'COCOA', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'SUGARCANE', 120);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'TOBACCO', 20);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'TEA', 200);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'CUBA', 'VANILLA', 70);

-- TODO: GUATEMALA
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'SUGARCANE', 100);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'TOBACCO', 170);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'CITRUS', 80);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'TEA', 280);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'GOLD', 163);

insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'COFFEE', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'COCOA', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'SUGARCANE', 120);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'TOBACCO', 20);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'TEA', 200);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'GUATEMALA', 'VANILLA', 70);

-- TODO: PUERTO_RICO
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'SUGARCANE', 100);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'TOBACCO', 170);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'CITRUS', 80);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'TEA', 280);
insert into goods_start (id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'GOLD', 163);

insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'COFFEE', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'COCOA', 240);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'SUGARCANE', 120);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'TOBACCO', 20);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'TEA', 200);
insert into goods_end(id, country, type, amount)
VALUES (uuid_generate_v4(), 'PUERTO_RICO', 'VANILLA', 70);
