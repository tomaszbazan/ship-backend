create table goods_start
(
    id          uuid    not null
        constraint goods_start_pk
            primary key,
    country varchar(255) not null
        constraint country_check
            check ( country in ( 'JAMAICA', 'HAITI', 'CUBA', 'GUATEMALA', 'PUERTO_RICO') )
);

create table goods_end
(
    id          uuid    not null
        constraint goods_end_pk
            primary key,
    country varchar(255) not null,
    constraint country_check
        check ( country in ( 'JAMAICA', 'HAITI', 'CUBA', 'GUATEMALA', 'PUERTO_RICO') )
);

create table goods
(
    id          uuid    not null
        constraint goods_pk
            primary key,
    type        varchar not null
        constraint type_check
            check ( type in ('COFFEE', 'COCOA', 'SUGARCANE', 'COTTON', 'TOBACCO', 'TEA', 'VANILLA', 'CITRUS', 'GOLD') ),
    amount      integer not null
        constraint amount_check
            check ( amount > 0 ),
    player_in_game_id    uuid
        constraint player_in_game_fk
            references player_in_game,
    goods_start_id    uuid
        constraint goods_start_fk
            references goods_start,
    goods_end_id    uuid
        constraint goods_end_fk
            references goods_end,
    card_id     uuid
        constraint card_fk
            references card,
    event_reward_id uuid
        constraint event_reward_fk
            references event_reward
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- JAMAICA
insert into goods_start (id, country) values ('0b9a8f14-5791-4f01-8f0c-f69ba75bbee8', 'JAMAICA');
insert into goods_end (id, country) values ('4b2f2fe1-f39a-4d11-812d-b95048d010e4', 'JAMAICA');

insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 100, '0b9a8f14-5791-4f01-8f0c-f69ba75bbee8');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TOBACCO', 170, '0b9a8f14-5791-4f01-8f0c-f69ba75bbee8');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'CITRUS', 80, '0b9a8f14-5791-4f01-8f0c-f69ba75bbee8');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TEA', 280, '0b9a8f14-5791-4f01-8f0c-f69ba75bbee8');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'GOLD', 163, '0b9a8f14-5791-4f01-8f0c-f69ba75bbee8');

insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COFFEE', 240, '4b2f2fe1-f39a-4d11-812d-b95048d010e4');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COCOA', 240, '4b2f2fe1-f39a-4d11-812d-b95048d010e4');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 120, '4b2f2fe1-f39a-4d11-812d-b95048d010e4');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TOBACCO', 20, '4b2f2fe1-f39a-4d11-812d-b95048d010e4');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TEA', 200, '4b2f2fe1-f39a-4d11-812d-b95048d010e4');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'VANILLA', 70, '4b2f2fe1-f39a-4d11-812d-b95048d010e4');


-- TODO: HAITI
insert into goods_start (id, country) values ('2e226a4e-ce82-43cb-bf10-90b36e57c6d9','HAITI');
insert into goods_end (id, country) values ('e903ae28-4608-4a15-872d-e0dd5a17669c','HAITI');

insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 100, '2e226a4e-ce82-43cb-bf10-90b36e57c6d9');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TOBACCO', 170, '2e226a4e-ce82-43cb-bf10-90b36e57c6d9');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'CITRUS', 80, '2e226a4e-ce82-43cb-bf10-90b36e57c6d9');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TEA', 280, '2e226a4e-ce82-43cb-bf10-90b36e57c6d9');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'GOLD', 163, '2e226a4e-ce82-43cb-bf10-90b36e57c6d9');

insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COFFEE', 240, 'e903ae28-4608-4a15-872d-e0dd5a17669c');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COCOA', 240, 'e903ae28-4608-4a15-872d-e0dd5a17669c');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 120, 'e903ae28-4608-4a15-872d-e0dd5a17669c');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TOBACCO', 20, 'e903ae28-4608-4a15-872d-e0dd5a17669c');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TEA', 200, 'e903ae28-4608-4a15-872d-e0dd5a17669c');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'VANILLA', 70, 'e903ae28-4608-4a15-872d-e0dd5a17669c');

-- TODO: CUBA
insert into goods_start (id, country) values ('10503c4d-526d-4120-921b-019798e6b30d','CUBA');
insert into goods_end (id, country) values ('052f325f-8bf8-416d-b8aa-d7565260b760','CUBA');

insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 100, '10503c4d-526d-4120-921b-019798e6b30d');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TOBACCO', 170, '10503c4d-526d-4120-921b-019798e6b30d');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'CITRUS', 80, '10503c4d-526d-4120-921b-019798e6b30d');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TEA', 280, '10503c4d-526d-4120-921b-019798e6b30d');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'GOLD', 163, '10503c4d-526d-4120-921b-019798e6b30d');

insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COFFEE', 240, '052f325f-8bf8-416d-b8aa-d7565260b760');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COCOA', 240, '052f325f-8bf8-416d-b8aa-d7565260b760');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 120, '052f325f-8bf8-416d-b8aa-d7565260b760');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TOBACCO', 20, '052f325f-8bf8-416d-b8aa-d7565260b760');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TEA', 200, '052f325f-8bf8-416d-b8aa-d7565260b760');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'VANILLA', 70, '052f325f-8bf8-416d-b8aa-d7565260b760');

-- TODO: GUATEMALA
insert into goods_start (id, country) values ('374b2c62-5d57-4215-8389-e8339540edca','GUATEMALA');
insert into goods_end (id, country) values ('fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84','GUATEMALA');

insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 100, '374b2c62-5d57-4215-8389-e8339540edca');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TOBACCO', 170, '374b2c62-5d57-4215-8389-e8339540edca');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'CITRUS', 80, '374b2c62-5d57-4215-8389-e8339540edca');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TEA', 280, '374b2c62-5d57-4215-8389-e8339540edca');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'GOLD', 163, '374b2c62-5d57-4215-8389-e8339540edca');

insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COFFEE', 240, 'fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COCOA', 240, 'fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 120, 'fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TOBACCO', 20, 'fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TEA', 200, 'fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'VANILLA', 70, 'fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84');

-- TODO: PUERTO_RICO
insert into goods_start (id, country) values ('420033ec-1241-476d-a2a0-2236f14f8f73','PUERTO_RICO');
insert into goods_end (id, country) values ('985f9e4c-5a4b-4b75-834b-c36125af318a','PUERTO_RICO');

insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 100, '420033ec-1241-476d-a2a0-2236f14f8f73');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TOBACCO', 170, '420033ec-1241-476d-a2a0-2236f14f8f73');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'CITRUS', 80, '420033ec-1241-476d-a2a0-2236f14f8f73');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'TEA', 280, '420033ec-1241-476d-a2a0-2236f14f8f73');
insert into goods (id, type, amount, goods_start_id) VALUES (uuid_generate_v4(), 'GOLD', 163, '420033ec-1241-476d-a2a0-2236f14f8f73');

insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COFFEE', 240, '985f9e4c-5a4b-4b75-834b-c36125af318a');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'COCOA', 240, '985f9e4c-5a4b-4b75-834b-c36125af318a');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 120, '985f9e4c-5a4b-4b75-834b-c36125af318a');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TOBACCO', 20, '985f9e4c-5a4b-4b75-834b-c36125af318a');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'TEA', 200, '985f9e4c-5a4b-4b75-834b-c36125af318a');
insert into goods (id, type, amount, goods_end_id) VALUES (uuid_generate_v4(), 'VANILLA', 70, '985f9e4c-5a4b-4b75-834b-c36125af318a');
