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
            check ( amount > 0 )
);

create table player_in_game_goods
(
    id          uuid    not null
        constraint player_in_game_goods_pk
            primary key,
    player_in_game_id uuid not null
        constraint player_in_game_goods_player_in_game_fk
            references player_in_game,
    goods_id uuid not null
        constraint player_in_game_goods_goods_fk
            references goods
);

create table goods_start
(
    id          uuid    not null
        constraint goods_start_pk
            primary key,
    country varchar(255) not null
        constraint country_check
            check ( country in ( 'JAMAICA', 'HAITI', 'CUBA', 'GUATEMALA', 'PUERTO_RICO') ),
    goods_id uuid not null
        constraint goods_start_goods_fk
            references goods
);

create table goods_end
(
    id          uuid    not null
        constraint goods_end_pk
            primary key,
    country varchar(255) not null,
        constraint country_check
            check ( country in ( 'JAMAICA', 'HAITI', 'CUBA', 'GUATEMALA', 'PUERTO_RICO') ),
    goods_id uuid not null
        constraint goods_end_goods_fk
            references goods
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- JAMAICA
insert into goods (id, type, amount) VALUES ('0b9a8f14-5791-4f01-8f0c-f69ba75bbee8', 'SUGARCANE', 100);
insert into goods (id, type, amount) VALUES ('761ab863-80d6-4056-83d7-aa6d52e1eeb6', 'TOBACCO', 170);
insert into goods (id, type, amount) VALUES ('a1f689c5-3ab4-44ea-a31b-048e501c7caf', 'CITRUS', 80);
insert into goods (id, type, amount) VALUES ('5a6dc041-009a-484b-ae30-e61af80f84cd', 'TEA', 280);
insert into goods (id, type, amount) VALUES ('13ed68d6-e963-4a05-a415-378a06f1fc09', 'GOLD', 163);

insert into goods (id, type, amount) VALUES ('4b2f2fe1-f39a-4d11-812d-b95048d010e4', 'COFFEE', 240);
insert into goods (id, type, amount) VALUES ('5a2534a3-0a22-4cd5-8b90-4f6cf18e8ca6', 'COCOA', 240);
insert into goods (id, type, amount) VALUES ('c9236b1c-5cec-46e7-8d65-33ab9e00a8a8', 'SUGARCANE', 120);
insert into goods (id, type, amount) VALUES ('3b1f0a35-27ee-4514-9668-02ef7b055823', 'TOBACCO', 20);
insert into goods (id, type, amount) VALUES ('02951810-2118-4f2e-9c30-7c3cc51503c6', 'TEA', 200);
insert into goods (id, type, amount) VALUES ('88a73eaf-3191-4a2e-927f-fe5494278a6f', 'VANILLA', 70);

insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','0b9a8f14-5791-4f01-8f0c-f69ba75bbee8');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','761ab863-80d6-4056-83d7-aa6d52e1eeb6');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','a1f689c5-3ab4-44ea-a31b-048e501c7caf');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','a1f689c5-3ab4-44ea-a31b-048e501c7caf');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','13ed68d6-e963-4a05-a415-378a06f1fc09');

insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','4b2f2fe1-f39a-4d11-812d-b95048d010e4');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','5a2534a3-0a22-4cd5-8b90-4f6cf18e8ca6');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','c9236b1c-5cec-46e7-8d65-33ab9e00a8a8');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','3b1f0a35-27ee-4514-9668-02ef7b055823');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','02951810-2118-4f2e-9c30-7c3cc51503c6');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'JAMAICA','88a73eaf-3191-4a2e-927f-fe5494278a6f');

-- TODO: HAITI
insert into goods (id, type, amount) VALUES ('2e226a4e-ce82-43cb-bf10-90b36e57c6d9', 'SUGARCANE', 100);
insert into goods (id, type, amount) VALUES ('ae8b1626-9ba0-4208-98b5-23f667152f8c', 'TOBACCO', 170);
insert into goods (id, type, amount) VALUES ('142ca05b-f037-4ca6-aacf-e28dbc73a4d5', 'CITRUS', 80);
insert into goods (id, type, amount) VALUES ('5613e1a3-6e6e-4b3f-9af9-93967a12b499', 'TEA', 280);
insert into goods (id, type, amount) VALUES ('f02ad490-aacb-41d7-9ec1-83a339a7b632', 'GOLD', 163);

insert into goods (id, type, amount) VALUES ('e903ae28-4608-4a15-872d-e0dd5a17669c', 'COFFEE', 240);
insert into goods (id, type, amount) VALUES ('8b01ab32-cf5e-4e61-8de7-024dc2479a41', 'COCOA', 240);
insert into goods (id, type, amount) VALUES ('ee521583-6a56-4078-b493-58392b653cb4', 'SUGARCANE', 120);
insert into goods (id, type, amount) VALUES ('823f0d7f-eb37-4379-a29e-1505e84c10fd', 'TOBACCO', 20);
insert into goods (id, type, amount) VALUES ('f644bcd2-77e1-4e21-806e-6f7f12dbf26f', 'TEA', 200);
insert into goods (id, type, amount) VALUES ('5c3acfda-25d7-4bed-b947-a542552480d4', 'VANILLA', 70);

insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'HAITI','2e226a4e-ce82-43cb-bf10-90b36e57c6d9');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'HAITI','ae8b1626-9ba0-4208-98b5-23f667152f8c');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'HAITI','142ca05b-f037-4ca6-aacf-e28dbc73a4d5');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'HAITI','5613e1a3-6e6e-4b3f-9af9-93967a12b499');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'HAITI','f02ad490-aacb-41d7-9ec1-83a339a7b632');

insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'HAITI','e903ae28-4608-4a15-872d-e0dd5a17669c');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'HAITI','8b01ab32-cf5e-4e61-8de7-024dc2479a41');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'HAITI','ee521583-6a56-4078-b493-58392b653cb4');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'HAITI','823f0d7f-eb37-4379-a29e-1505e84c10fd');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'HAITI','f644bcd2-77e1-4e21-806e-6f7f12dbf26f');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'HAITI','5c3acfda-25d7-4bed-b947-a542552480d4');

-- TODO: CUBA
insert into goods (id, type, amount) VALUES ('10503c4d-526d-4120-921b-019798e6b30d', 'SUGARCANE', 100);
insert into goods (id, type, amount) VALUES ('625f6d36-6e44-4d63-93a2-92f2bfbff2cd', 'TOBACCO', 170);
insert into goods (id, type, amount) VALUES ('91d60040-108f-47aa-8fe7-8c3506605919', 'CITRUS', 80);
insert into goods (id, type, amount) VALUES ('e5e6c880-c728-4e06-a2cf-414a4bc3dd8b', 'TEA', 280);
insert into goods (id, type, amount) VALUES ('62de662e-8311-4d62-9083-f07409a5efbd', 'GOLD', 163);

insert into goods (id, type, amount) VALUES ('052f325f-8bf8-416d-b8aa-d7565260b760', 'COFFEE', 240);
insert into goods (id, type, amount) VALUES ('b61836d6-c688-472f-92b4-927506e66a27', 'COCOA', 240);
insert into goods (id, type, amount) VALUES ('7886c029-5d58-473e-b28c-b000f2e4a933', 'SUGARCANE', 120);
insert into goods (id, type, amount) VALUES ('6189783d-8b50-4dc2-80e0-243bb9ab5605', 'TOBACCO', 20);
insert into goods (id, type, amount) VALUES ('f6f91750-4ada-4989-8eba-307d273a550b', 'TEA', 200);
insert into goods (id, type, amount) VALUES ('d75aeb93-cf28-40f1-9912-71f585caf51b', 'VANILLA', 70);

insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'CUBA','10503c4d-526d-4120-921b-019798e6b30d');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'CUBA','625f6d36-6e44-4d63-93a2-92f2bfbff2cd');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'CUBA','91d60040-108f-47aa-8fe7-8c3506605919');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'CUBA','e5e6c880-c728-4e06-a2cf-414a4bc3dd8b');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'CUBA','62de662e-8311-4d62-9083-f07409a5efbd');

insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'CUBA','052f325f-8bf8-416d-b8aa-d7565260b760');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'CUBA','b61836d6-c688-472f-92b4-927506e66a27');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'CUBA','7886c029-5d58-473e-b28c-b000f2e4a933');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'CUBA','6189783d-8b50-4dc2-80e0-243bb9ab5605');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'CUBA','f6f91750-4ada-4989-8eba-307d273a550b');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'CUBA','d75aeb93-cf28-40f1-9912-71f585caf51b');

-- TODO: GUATEMALA
insert into goods (id, type, amount) VALUES ('374b2c62-5d57-4215-8389-e8339540edca', 'SUGARCANE', 100);
insert into goods (id, type, amount) VALUES ('fc032182-1c33-4b48-83dc-06844bfc4c58', 'TOBACCO', 170);
insert into goods (id, type, amount) VALUES ('f51cb36d-a9aa-4df4-aa7e-f1dbd2170841', 'CITRUS', 80);
insert into goods (id, type, amount) VALUES ('ab029f65-0964-4054-825b-218fa3b42b2a', 'TEA', 280);
insert into goods (id, type, amount) VALUES ('42f86f27-c3e3-4e2d-bc52-fd467303ac3c', 'GOLD', 163);

insert into goods (id, type, amount) VALUES ('fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84', 'COFFEE', 240);
insert into goods (id, type, amount) VALUES ('cb88c9e3-5cea-4187-8b3d-f7c0406644dc', 'COCOA', 240);
insert into goods (id, type, amount) VALUES ('0fb6c9f3-9697-4aaa-a891-f7e4fa76ce3a', 'SUGARCANE', 120);
insert into goods (id, type, amount) VALUES ('3f57c704-aee7-4003-9c4a-74fc1a60fbab', 'TOBACCO', 20);
insert into goods (id, type, amount) VALUES ('5ca729e0-7ebb-4f65-b7a4-a58006d3d159', 'TEA', 200);
insert into goods (id, type, amount) VALUES ('6d07c861-fc0a-41ca-87ee-e6ee2cf9373d', 'VANILLA', 70);

insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','374b2c62-5d57-4215-8389-e8339540edca');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','fc032182-1c33-4b48-83dc-06844bfc4c58');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','f51cb36d-a9aa-4df4-aa7e-f1dbd2170841');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','ab029f65-0964-4054-825b-218fa3b42b2a');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','42f86f27-c3e3-4e2d-bc52-fd467303ac3c');

insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','fd60385b-5d4d-4c6d-8ed6-71d0ecb68c84');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','cb88c9e3-5cea-4187-8b3d-f7c0406644dc');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','0fb6c9f3-9697-4aaa-a891-f7e4fa76ce3a');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','3f57c704-aee7-4003-9c4a-74fc1a60fbab');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','5ca729e0-7ebb-4f65-b7a4-a58006d3d159');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'GUATEMALA','6d07c861-fc0a-41ca-87ee-e6ee2cf9373d');

-- TODO: PUERTO_RICO
insert into goods (id, type, amount) VALUES ('420033ec-1241-476d-a2a0-2236f14f8f73', 'SUGARCANE', 100);
insert into goods (id, type, amount) VALUES ('0d3f3a29-9846-49a3-add1-91d3c7e7b7ed', 'TOBACCO', 170);
insert into goods (id, type, amount) VALUES ('d53aeb05-84e5-46d8-b80b-17a08d64333f', 'CITRUS', 80);
insert into goods (id, type, amount) VALUES ('f042d5b0-c654-4613-bfdb-248e2eb91a8d', 'TEA', 280);
insert into goods (id, type, amount) VALUES ('18ee1ac3-e155-42bf-95c3-f36dbab022a5', 'GOLD', 163);

insert into goods (id, type, amount) VALUES ('985f9e4c-5a4b-4b75-834b-c36125af318a', 'COFFEE', 240);
insert into goods (id, type, amount) VALUES ('be696953-5442-45ac-98bb-c09a0cfd0644', 'COCOA', 240);
insert into goods (id, type, amount) VALUES ('fdbd76d2-cc9d-42df-b76c-eec517bddb1b', 'SUGARCANE', 120);
insert into goods (id, type, amount) VALUES ('6923c5f7-e5e5-4e97-883d-4baa6c83df4c', 'TOBACCO', 20);
insert into goods (id, type, amount) VALUES ('e78341a8-f4de-414d-a49e-8c17f81c9342', 'TEA', 200);
insert into goods (id, type, amount) VALUES ('98b58f9a-2aa0-4fb6-b83c-ca351deca72e', 'VANILLA', 70);

insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','420033ec-1241-476d-a2a0-2236f14f8f73');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','0d3f3a29-9846-49a3-add1-91d3c7e7b7ed');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','d53aeb05-84e5-46d8-b80b-17a08d64333f');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','f042d5b0-c654-4613-bfdb-248e2eb91a8d');
insert into goods_start (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','18ee1ac3-e155-42bf-95c3-f36dbab022a5');

insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','985f9e4c-5a4b-4b75-834b-c36125af318a');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','be696953-5442-45ac-98bb-c09a0cfd0644');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','fdbd76d2-cc9d-42df-b76c-eec517bddb1b');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','6923c5f7-e5e5-4e97-883d-4baa6c83df4c');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','e78341a8-f4de-414d-a49e-8c17f81c9342');
insert into goods_end (id, country, goods_id) values (uuid_generate_v4(),'PUERTO_RICO','98b58f9a-2aa0-4fb6-b83c-ca351deca72e');
