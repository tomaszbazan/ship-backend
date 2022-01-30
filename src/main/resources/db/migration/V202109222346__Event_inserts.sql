CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 4, 2
INSERT INTO event_reward (id, x, y) VALUES ('459da7f9-b706-451c-86fc-4e5fba94c2fd', 4, 2);
INSERT INTO card (id, type, event_reward_id) VALUES ('58fff45a-df94-4fcc-adb7-b99d704d8774', 'SNAIL', '459da7f9-b706-451c-86fc-4e5fba94c2fd');
INSERT INTO goods (id, type, amount, card_id) VALUES (uuid_generate_v4(), 'GOLD', 5, '58fff45a-df94-4fcc-adb7-b99d704d8774');
--
-- -- 8, 3
-- INSERT INTO card (id, type) VALUES ('86deb22b-e84b-48fc-a6df-dc1dd8420472', 'RUM');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 8, 3, null, '86deb22b-e84b-48fc-a6df-dc1dd8420472');
--
-- -- 4, 4
-- INSERT INTO goods (id, type, amount) VALUES ('8528d166-ae23-46fe-9c09-4dcb676ece74', 'COFFEE', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('481b5f12-e9d5-4818-b991-8adfa3524c8a', 'SUGARCANE', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('9b0a971b-695c-4539-820a-d0f30c9c42ec', 'CITRUS', 10);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 4, 4, '8528d166-ae23-46fe-9c09-4dcb676ece74', null);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 4, 4, '481b5f12-e9d5-4818-b991-8adfa3524c8a', null);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 4, 4, '9b0a971b-695c-4539-820a-d0f30c9c42ec', null);
--
-- -- 5, 4
-- INSERT INTO goods (id, type, amount) VALUES ('19f04377-5a51-40f7-9553-84b6051f816a', 'VANILLA', 10);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 5, 4, '19f04377-5a51-40f7-9553-84b6051f816a', null);
--
-- -- 2, 5
-- INSERT INTO card (id, type) VALUES ('734b56b6-3576-4d93-8882-7212a65b8bdc', 'RUM');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 2, 5, null, '734b56b6-3576-4d93-8882-7212a65b8bdc');
--
-- -- 4, 2
-- INSERT INTO card (id, type) VALUES ('07c3499c-354e-46e1-96bb-ee5a38b9908b', 'SNAIL');
-- INSERT INTO goods (id, type, amount) VALUES ('de95ab5d-fc31-4e6e-b085-bb966c098a03', 'GOLD', 5);
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '07c3499c-354e-46e1-96bb-ee5a38b9908b', 'de95ab5d-fc31-4e6e-b085-bb966c098a03');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 4, 2, null, '07c3499c-354e-46e1-96bb-ee5a38b9908b');
--
-- -- 2, 7
-- INSERT INTO goods (id, type, amount) VALUES ('3c0372e0-31db-4b95-b22d-f345f34efef6', 'COFFEE', 60);
-- INSERT INTO goods (id, type, amount) VALUES ('8445aceb-fc75-43ee-bf49-936a5a8d1e99', 'SUGARCANE', 20);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 2, 7, '3c0372e0-31db-4b95-b22d-f345f34efef6', null);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 2, 7, '8445aceb-fc75-43ee-bf49-936a5a8d1e99', null);
--
-- -- 4, 8
-- INSERT INTO goods (id, type, amount) VALUES ('39d565a1-a107-41aa-81d1-4a04bebbf98c', 'TOBACCO', 30);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 4, 8, '39d565a1-a107-41aa-81d1-4a04bebbf98c', null);
--
-- -- 6, 8
-- INSERT INTO goods (id, type, amount) VALUES ('a3e8f33f-a46b-4aa1-8fc3-2a6c72ceb4c9', 'TOBACCO', 30);
-- INSERT INTO goods (id, type, amount) VALUES ('227a5dd7-48a0-465b-bc50-1c0e3ca612a2', 'VANILLA', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('849d1b97-7571-43ee-805d-7a52002104e3', 'COCOA', 30);
-- INSERT INTO goods (id, type, amount) VALUES ('96b7f44f-afcd-4617-8250-a9bdc1497ff6', 'SUGARCANE', 10);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 6, 8, 'a3e8f33f-a46b-4aa1-8fc3-2a6c72ceb4c9', null);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 6, 8, '227a5dd7-48a0-465b-bc50-1c0e3ca612a2', null);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 6, 8, '849d1b97-7571-43ee-805d-7a52002104e3', null);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 6, 8, '96b7f44f-afcd-4617-8250-a9bdc1497ff6', null);
--
-- -- 7, 8
-- INSERT INTO card (id, type) VALUES ('103a8f8b-9eff-4467-8633-060497aa5f1e', 'SICK');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 7, 7, null, '103a8f8b-9eff-4467-8633-060497aa5f1e');
--
-- -- 5, 9
-- INSERT INTO card (id, type) VALUES ('8ac0867b-5e37-47bb-8080-f451fc34aab5', 'SICK');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 5, 9, null, '8ac0867b-5e37-47bb-8080-f451fc34aab5');
--
-- -- 3, 4
-- INSERT INTO card (id, type) VALUES ('fe3e9f7e-2aa2-4113-a184-cb35327fb551', 'CURE');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 3, 4, null, 'fe3e9f7e-2aa2-4113-a184-cb35327fb551');
--
-- -- 8, 4
-- INSERT INTO card (id, type) VALUES ('0767c29b-f3d9-4555-b89c-74910aa5f441', 'CURE');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 8, 4, null, '0767c29b-f3d9-4555-b89c-74910aa5f441');
--
-- -- 5, 5
-- INSERT INTO goods (id, type, amount) VALUES ('3772d08e-beea-468b-8dc8-2704c46fa873', 'GOLD', 50);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 5, 5, '3772d08e-beea-468b-8dc8-2704c46fa873', null);
--
-- -- 1, 6
-- INSERT INTO goods (id, type, amount) VALUES ('506b178c-6c91-43a1-8c10-5b9e23d471bc', 'CITRUS', 20);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 1, 6, '506b178c-6c91-43a1-8c10-5b9e23d471bc', null);
--
-- -- 4, 6
-- INSERT INTO goods (id, type, amount) VALUES ('2f53eba4-933c-41e3-97fc-256a3ea6dc9f', 'GOLD', 50);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 4, 6, '2f53eba4-933c-41e3-97fc-256a3ea6dc9f', null);
--
-- -- 8, 6
-- INSERT INTO card (id, type, conjunction) VALUES ('0e5968d3-c9c7-42fc-985c-d906ced77f98', 'BILL_OF_EXCHANGE', 'OR');
-- INSERT INTO goods (id, type, amount) VALUES ('0223ed32-ff03-4199-bf04-836321b4af23', 'COFFEE', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('9554a913-c934-46a4-8862-343e893b9224', 'COCOA', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('7a28bf10-4b68-4cce-962f-782c4cc06063', 'SUGARCANE', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('e77fffff-f7b5-4984-a2c7-d04b68843548', 'COTTON', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('1a264bad-6127-40f9-a5e7-9fd0b4cdcf94', 'TOBACCO', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('5bdfab68-175e-4d48-a2b4-a755b448cd8a', 'TEA', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('b2b1e3d1-b657-400c-b21f-c86893a6da67', 'VANILLA', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('b0e3c5cf-911d-48ca-ba22-e3b38eefd71d', 'CITRUS', 10);
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '0e5968d3-c9c7-42fc-985c-d906ced77f98', '0223ed32-ff03-4199-bf04-836321b4af23');
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '0e5968d3-c9c7-42fc-985c-d906ced77f98', '9554a913-c934-46a4-8862-343e893b9224');
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '0e5968d3-c9c7-42fc-985c-d906ced77f98', '7a28bf10-4b68-4cce-962f-782c4cc06063');
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '0e5968d3-c9c7-42fc-985c-d906ced77f98', 'e77fffff-f7b5-4984-a2c7-d04b68843548');
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '0e5968d3-c9c7-42fc-985c-d906ced77f98', '1a264bad-6127-40f9-a5e7-9fd0b4cdcf94');
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '0e5968d3-c9c7-42fc-985c-d906ced77f98', '5bdfab68-175e-4d48-a2b4-a755b448cd8a');
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '0e5968d3-c9c7-42fc-985c-d906ced77f98', 'b2b1e3d1-b657-400c-b21f-c86893a6da67');
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '0e5968d3-c9c7-42fc-985c-d906ced77f98', 'b0e3c5cf-911d-48ca-ba22-e3b38eefd71d');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 8, 6, null, '0e5968d3-c9c7-42fc-985c-d906ced77f98');
--
-- -- 2, 2
-- INSERT INTO card (id, type) VALUES ('7713bfe7-c9e9-45b9-859d-abedd88a8a24', 'EXCHANGE');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 2, 2, null, '7713bfe7-c9e9-45b9-859d-abedd88a8a24');
--
-- -- 3, 2
INSERT INTO event_reward (id, x, y) VALUES ('bfcde53e-876b-40e3-b037-4b0150abe209', 3, 2);
INSERT INTO goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'COTTON', 10, 'bfcde53e-876b-40e3-b037-4b0150abe209');
--
-- -- 6, 2
-- INSERT INTO card (id, type) VALUES ('128b49b6-ee97-43cb-8d91-ea4ee46d30e9', 'DEPOSIT');
-- INSERT INTO goods (id, type, amount) VALUES ('b5263140-71a5-482f-897d-5a1f99075a32', 'TEA', 40);
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '128b49b6-ee97-43cb-8d91-ea4ee46d30e9', 'b5263140-71a5-482f-897d-5a1f99075a32');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 6, 2, null, '128b49b6-ee97-43cb-8d91-ea4ee46d30e9');
--
-- -- 3, 3
-- INSERT INTO goods (id, type, amount) VALUES ('4abe0682-796b-453c-87b2-266c7e7726fd', 'COCOA', 30);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 3, 3, '4abe0682-796b-453c-87b2-266c7e7726fd', null);
--
-- -- 1, 4
-- INSERT INTO goods (id, type, amount) VALUES ('c9ac00b0-b59c-4a82-855a-7eb01b22014e', 'COTTON', 10);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 1, 4, 'c9ac00b0-b59c-4a82-855a-7eb01b22014e', null);
--
-- -- 7, 4
-- INSERT INTO goods (id, type, amount) VALUES ('2a9e8331-4e76-41a0-996e-5f4ecd1898e9', 'VANILLA', 10);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 7, 4, '2a9e8331-4e76-41a0-996e-5f4ecd1898e9', null);
--
-- -- 7, 5
-- INSERT INTO card (id, type) VALUES ('2ecb6d80-b087-4d40-935d-c4b91a330423', 'BILL_OF_EXCHANGE');
-- INSERT INTO goods (id, type, amount) VALUES ('fec40a48-acf0-4088-a917-c7861ad68cc8', 'GOLD', 10);
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '2ecb6d80-b087-4d40-935d-c4b91a330423', 'fec40a48-acf0-4088-a917-c7861ad68cc8');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 7, 5, null, '2ecb6d80-b087-4d40-935d-c4b91a330423');
--
-- -- 8, 5
-- INSERT INTO goods (id, type, amount) VALUES ('b1141b93-626e-4555-b089-011e8e1211e9', 'COCOA', 30);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 8, 5, 'b1141b93-626e-4555-b089-011e8e1211e9', null);
--
-- -- 3, 6
-- INSERT INTO card (id, type) VALUES ('8871f857-2226-483e-b1e3-ccdb444cc6e6', 'SHOPPING_LIST');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 3, 6, null, '8871f857-2226-483e-b1e3-ccdb444cc6e6');
--
-- -- 6, 7
-- INSERT INTO card (id, type) VALUES ('a8e7f45f-daa8-455b-9505-3f37a41c526b', 'DEPOSIT');
-- INSERT INTO goods (id, type, amount) VALUES ('7b296555-4853-49f5-bdfa-3ae4aabe9a59', 'COTTON', 50);
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), 'a8e7f45f-daa8-455b-9505-3f37a41c526b', '7b296555-4853-49f5-bdfa-3ae4aabe9a59');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 6, 7, null, 'a8e7f45f-daa8-455b-9505-3f37a41c526b');
--
-- -- 2, 8
-- INSERT INTO card (id, type) VALUES ('e33edabe-f76c-4542-940a-2a84a4f6571a', 'SHOPPING_LIST');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 2, 8, null, 'e33edabe-f76c-4542-940a-2a84a4f6571a');
--
-- -- 3, 8
-- INSERT INTO goods (id, type, amount) VALUES ('debda62f-54d9-4d4c-a46a-b4b6f1d9bb5e', 'VANILLA', 10);
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 3, 8, 'debda62f-54d9-4d4c-a46a-b4b6f1d9bb5e', null);
--
-- -- 5, 8
-- INSERT INTO card (id, type) VALUES ('e8311209-96eb-4603-9280-b7219082b28d', 'BILL_OF_EXCHANGE');
-- INSERT INTO goods (id, type, amount) VALUES ('e9f18a3d-f352-49cb-9ee2-ca5a47d4cfd3', 'GOLD', 10);
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), 'e8311209-96eb-4603-9280-b7219082b28d', 'e9f18a3d-f352-49cb-9ee2-ca5a47d4cfd3');
-- INSERT INTO event_reward (id, x, y, goods_id, card_id) VALUES (uuid_generate_v4(), 5, 8, null, 'e8311209-96eb-4603-9280-b7219082b28d');
--
-- -- question reward
-- INSERT INTO card (id, type) VALUES ('3769cbbb-92d6-4872-bb5e-ce35e086e19e', 'BILL_OF_EXCHANGE');
-- INSERT INTO goods (id, type, amount) VALUES ('724eeb77-c510-4da4-ad07-607ccad272d8', 'TEA', 10);
-- INSERT INTO goods (id, type, amount) VALUES ('6c79d6ce-ce71-4602-9fc9-a80ae2035a3d', 'COFFEE', 20);
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '3769cbbb-92d6-4872-bb5e-ce35e086e19e', '724eeb77-c510-4da4-ad07-607ccad272d8');
-- INSERT INTO card_goods (id, card_id, goods_id) VALUES (uuid_generate_v4(), '3769cbbb-92d6-4872-bb5e-ce35e086e19e', '6c79d6ce-ce71-4602-9fc9-a80ae2035a3d');

-- todo: 4, 5; 7, 7

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 2, 'ADVENTURE', 'TEST', 'Znalazłeś bardzo ciekawy okaz muszelki, która należała do niezwykle rzadko spotykanego ślimaka morskiego Strombus Gigas.
W porcie będziesz mógł za nią dostać 5 sztabek złota. Jeśli dodatkowo już teraz poprawnie odpowiesz na zagadkę, będziesz mógł wliczyć do swojego bilansu na koniec gry 10 ton herbaty i 20 ton kawy (wliczają się do realizacji zamówienia handlowego, nie wliczają do ładowności statku).', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 3, 'ADVENTURE', 'TEST', 'Na zachrypniętą papugę!
-- Zza zakrętu wyłonił się szkuner piracki Kapitana Moczymordy! Nie pozostaje nic innego jak rzucić kośćmi.
-- 1 lub 2: Kapitan Moczymorda to twój dawny druh i kompan, ucieszył się na wasz widok ale musi płynąć dalej i ścigać innych frajerów – mijacie się serdecznie i każdy płynie w swoją stronę
-- 3 lub 4: Kapitan Moczymorda to twój dawny druh i kompan, ucieszył się na wasz widok, tym bardziej, że byłeś mu winny trochę złota! Z wielkim żalem ale jednak wobec przewagi liczebnej armat Kapitana oddajesz połowę swojego złota w gotówce
-- 5 lub 6: Kapitan Moczymorda ucieszył się na wasz widok, tym bardziej, że ma trochę wolnego miejsca w swoich ładowniach. Wobec przewagi oddajesz bez walki Kapitanowi Moczymordzie 10 ton trzciny lub cytrusów', 'DICE', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 3, 'ADVENTURE', 'TEST', 'Fortuna wam sprzyja – ciepły prąd zatokowy El Ninio, pojawiający się na tutejszych wodach raz na cztery lata właśnie się pojawił! Dzięki niemu zyskujecie trochę czasu, gdyż z siłą wodospadu ponosi was akurat w tym kierunku, w którym chcecie płynąć! Zatem możecie teraz wykonać dodatkowy ruch.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 3, 'ADVENTURE', 'TEST', 'Rum, który otrzymałeś w zaprzyjaźnionej acz nielegalnej gorzelni nieźle daje niezłego kopa! Postanawiasz ukryć go przed załogą i zachować na później. Ty zadecydujesz kiedy opróżnicie baryłki niezwykłego trunku. Dzięki temu załoga dostanie nowej energii i będzie się uwijać dwa razy szybciej niż normalnie a przez to w jednej turze pokonacie odległość dwóch pól – zgłoś ten fakt przed wykonaniem ruchu, w dowolnym momencie rejsu (nie dotyczy was ewentualna zawartość pola przez które tylko przepływacie, oczywiście poza głębokością toru wodnego)', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 4, 'ADVENTURE', 'TEST', 'A niech to mewa kopnie! Tylko tego brakowało, żeby rozpętała się tropikalna burza skoro do portu jest już tak blisko! Mimo zażartej walki z żywiołem o zachowanie życia i towaru, tracisz część cennego ładunku – możesz wybrać czego tracisz 10 ton: kawy, trzciny cukrowej czy cytrusów. Ładunek wypada za burtę i tyle go widzieli.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 4, 'ADVENTURE', 'TEST', 'Wyłowiłeś skrzynię pełną skarbów! Oprócz prawie nieużywanych kalesonów, kapeluszy i opasek na oko pirata, znalazłeś w środku 10 ton wanilii.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 5, 'ADVENTURE', 'TEST', 'Rum, który otrzymałeś w zaprzyjaźnionej acz nielegalnej gorzelni nieźle daje niezłego kopa! Postanawiasz ukryć go przed załogą i zachować na później. Ty zadecydujesz kiedy opróżnicie baryłki niezwykłego trunku. Dzięki temu załoga dostanie nowej energii i będzie się uwijać dwa razy szybciej niż normalnie a przez to w jednej turze pokonacie odległość dwóch pól – zgłoś ten fakt przed wykonaniem ruchu, w dowolnym momencie rejsu. (nie dotyczy was ewentualna zawartość pola przez które tylko przepływacie, oczywiście poza głębokością toru wodnego)', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 5, 'ADVENTURE', 'TEST', 'Znalazłeś bardzo ciekawy okaz muszelki, która należała do niezwykle rzadko spotykanego ślimaka morskiego Strombus Gigas. W porcie będziesz mógł za nią dostać 5 sztabek złota. Jeśli dodatkowo już teraz poprawnie odpowiesz na zagadkę, będziesz mógł wliczyć do swojego bilansu na koniec gry 10 ton herbaty i 20 ton kawy (wliczają się do realizacji zamówienia handlowego, nie wliczają do ładowności statku).', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 6, 'ADVENTURE', 'TEST', 'Na zachrypniętą papugę!
-- Zza zakrętu wyłonił się szkuner piracki Kapitana Moczymordy! Nie pozostaje nic innego jak rzucić kośćmi
-- 1 lub 2: Kapitan Moczymorda to twój dawny druh i kompan, ucieszył się na wasz widok ale musi płynąć dalej i ścigać innych frajerów – mijacie się serdecznie i każdy płynie w swoją stronę
-- 3 lub 4: Kapitan Moczymorda to twój dawny druh i kompan, ucieszył się na wasz widok, tym bardziej, że byłeś mu winny trochę złota! Z wielkim żalem ale jednak wobec przewagi liczebnej armat Kapitana oddajesz połowę swojego złota w gotówce
-- 5 lub 6: Kapitan Moczymorda ucieszył się na wasz widok, tym bardziej, że ma trochę wolnego miejsca w swoich ładowniach. Wobec przewagi oddajesz bez walki Kapitanowi Moczymordzie 10 ton trzciny lub cytrusów).', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 7, 'ADVENTURE', 'TEST', 'Oto na horyzoncie zamajaczyły jakieś przedmioty w wodzie. Po dopłynięciu okazało się, że jakiś inny statek miał pecha bo zmiotło mu z pokładu w czasie ostatniej burzy trochę ładunku. Po wyłowieniu okazało się, że uzbieraliście w ten sposób 60 ton kawy i 20 ton trzciny cukrowej. Udane połowy! ', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 7, 'ADVENTURE', 'TEST', 'Fortuna wam sprzyja – ciepły prąd zatokowy El Ninio, pojawiający się na tutejszych wodach raz na cztery lata właśnie się pojawił! Dzięki niemu zyskujecie trochę czasu, gdyż z siłą wodospadu ponosi was akurat w tym kierunku, w którym chcecie płynąć! Zatem możecie teraz wykonać dodatkowy ruch.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 8, 'ADVENTURE', 'TEST', 'Wyłowiłeś skrzynię do połowy pełną skarbów! Oprócz zapasu drewnianych nóg, znalazłeś w środku 30 ton tytoniu.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 8, 'ADVENTURE', 'TEST', 'Oto na horyzoncie zamajaczyły jakieś przedmioty w wodzie. Po dopłynięciu okazało się, że jakiś inny statek miał pecha bo zmiotło mu z pokładu w czasie ostatniej burzy trochę ładunku. Po wyłowieniu okazało się, że uzbieraliście w ten sposób 30 ton tytoniu, 10 ton wanilii, 30 ton kakao i 10 ton trzciny cukrowej. Udane połowy!', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 8, 'ADVENTURE', 'TEST', 'Cała załoga zatruła się nieświeżymi rybami – wymiotom i biegunkom nie ma końca! W związku z tym nieszczęśliwym incydentem tracicie na kotwicy jeden dzień (jedną turą). (jeśli znasz recepturę lekarstwa na zatrucie pokarmowe możesz wyleczyć załogę i uniknąć straty czasu)', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 9, 'ADVENTURE', 'TEST', 'Cała załoga zatruła się nieświeżymi rybami – wymiotom i biegunkom nie ma końca! W związku z tym nieszczęśliwym incydentem tracicie na kotwicy jeden dzień (jedną turą). (jeśli znasz recepturę lekarstwa na zatrucie pokarmowe możesz wyleczyć załogę i uniknąć straty czasu)', 'QUESTION', false);
--
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 4, 'TREASURE', 'TEST', 'Znalazłeś maść z nietoperza – lekarstwo na wszelkie choroby.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 4, 'TREASURE', 'TEST', 'Znalazłeś maść z nietoperza – lekarstwo na wszelkie choroby.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 5, 'TREASURE', 'TEST', 'Skrzynia pełna złota a dokładnie 50 sztabek.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (1, 6, 'TREASURE', 'TEST', 'Zwrot podatku z urzędu podatków, ceł i opłat – otrzymujesz natychmiast 20 ton cytrusów.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 6, 'TREASURE', 'TEST', 'Skrzynia pełna złota a dokładnie 50 sztabek.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 6, 'TREASURE', 'TEST', 'JOKER – w porcie masz prawo zamienić JOKERA na 10 ton dowolnego towaru, który wliczy ci się do realizacji zamówienia handlowego
-- JOKER nie wlicza się do ładowności, może być przedmiotem handlu (zanim wpłyniesz do portu) na dowolnych zasadach', 'QUESTION', false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 2, 'BOTTLE', 'TEST', 'W butelce znajduje się weksel In blanco. Jeśli staniesz na jakimś polu z innym statkiem, możesz przedstawić weksel do zapłaty – zabierasz 10 ton towaru który chcesz ale w zamian zostawiasz 10 ton czegoś, co ci tylko zajmuje miejsce w ładowni. Jeśli staniesz na jednym polu z innym statkiem ale nie skorzystasz z weksla, traci on swoją ważność i staje się bezużytecznym kawałkiem papieru.', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 2, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton bawełny', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 2, 'BOTTLE', 'TEST', 'Kapitan Kuternoga był ci winien trochę herbaty. Niniejszy list jest kwitem depozytowym na 40 ton herbaty do realizacji w porcie. Herbata ta nie wlicza się do wagi statku ani do realizacji zamówienia handlowego. Wlicza się do ogólnego wyniku finansowego na koniec gry po cenach oficjalnych, możesz tym kwitem handlować jak każdym innym dobrem.', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 2, 'BOTTLE', 'TEST', 'GÓRSKIE POWIETRZE Z ANDÓW', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 3, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 30 ton kakao.', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 3, 'BOTTLE', 'TEST', 'W butelce znajduje się stary pergamin a na nim zaklęcie jasnowidzenia. WOW! Ekstra sprawa takie jasnowidzenie! Nie wiesz co to jest ale już to chcesz! Na wszelki wypadek każesz ewakuować załogę do szalup kiedy ty wypowiesz zaklęcie na głos. Huknęło, gruchnęło, pokład spowił dym jak po wybuchu beczki prochu ale ty widzisz wszystko wyraźnie jak nigdy! Sięgasz wzrokiem dużo dalej niż pozwala horyzont... (Sprawdź co kryje jedno, wybrane sąsiednie do waszej aktualnej pozycji pole. Załoga wraca na pokład a ty z miną admirała floty zarządzasz kierunek rejsu i ruszacie w dalszą podróż)', 'VISION', false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 3, 'BOTTLE', 'TEST', 'W butelce znajduje się weksel In blanco. Jeśli staniesz na jakimś polu z innym statkiem, możesz przedstawić weksel do zapłaty – zabierasz 10 ton towaru który chcesz ale w zamian zostawiasz 10 ton czegoś, co ci tylko zajmuje miejsce w ładowni. Jeśli staniesz na jednym polu z innym statkiem ale nie skorzystasz z weksla, traci on swoją ważność i staje się bezużytecznym kawałkiem papieru.', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (1, 4, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton bawełny', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 4, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton wanilii.', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 5, 'BOTTLE', 'TEST', 'Górskie powietrze z Andów', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 5, 'BOTTLE', 'TEST', 'Znalazłeś butelkę w której siedział Dżin Dariusz. Jeśli odgadniesz jego zagadkę spełni jedno twoje życzenie – albo otrzymasz 30 ton kakao albo 30 ton kawy. Jeśli nie odpowiesz lub odpowiesz nieprawidłowo Dżin zabiera ci 10 ton tytoniu i znika z towarem.', 'QUESTION', false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 5, 'BOTTLE', 'TEST', 'W butelce znajduje się kwit depozytowy o wartości 10 sztuk złota na okaziciela, do zrealizowania w porcie do którego płyniecie! A to ci fart!', 'QUESTION', false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 5, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 30 ton kakao.', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 6, 'BOTTLE', 'TEST', 'W butelce znajduje się potargany list o złowrogim tytule „Lista zakupów”. Niestety poza listą zakupów składników niezbędnych do wydania niedzielnego obiadu u Gubernatora Wyspy nie ma nic więcej! Zachowam go na później, może się do czegoś przyda...', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 6, 'BOTTLE', 'TEST', 'W butelce znajduje się stary pergamin a na nim zaklęcie jasnowidzenia. WOW! Ekstra sprawa takie jasnowidzenie! Nie wiesz co to jest ale już to chcesz! Na wszelki wypadek każesz ewakuować załogę do szalup kiedy ty wypowiesz zaklęcie na głos. Huknęło, gruchnęło, pokład spowił dym jak po wybuchu beczki prochu ale ty widzisz wszystko wyraźnie jak nigdy! Sięgasz wzrokiem dużo dalej niż pozwala horyzont... (Sprawdź co kryje jedno, wybrane sąsiednie do waszej aktualnej pozycji pole. Załoga wraca na pokład a ty z miną admirała floty zarządzasz kierunek rejsu i ruszacie w dalszą podróż)', 'VISION', false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 7, 'BOTTLE', 'TEST', 'W butelce znajduje się niedopita resztka jakiejś berbeluchy. Sądząc po smrodzie cieczy raczej nie kosztowała majątku. Na wszelki wypadek żeby nikt się nie zatruł wyrzucasz butelkę za burtę, zły że w ogóle zawracałeś sobie nią głowę! Czego spodziewałeś się znaleźć w pustej flaszce? Mapy ze skarbami piratów???', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 7, 'BOTTLE', 'TEST', 'Kapitan Drewniana Papuga był ci winien trochę bawełny. Niniejszy list jest kwitem depozytowym na 50 ton bawełny do realizacji w porcie. Bawełna ta nie wlicza się do wagi statku ale wlicza się do realizacji zamówienia handlowego w porcie i ogólnego wyniku finansowego na koniec gry po cenach oficjalnych, możesz tym kwitem handlować jak każdym innym dobrem.', 'QUESTION', false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 7, 'BOTTLE', 'TEST', 'Znalazłeś butelkę w której siedział Dżin Wacław. Jeśli odgadniesz jego zagadkę spełni jedno twoje życzenie – albo otrzymasz 30 ton trzciny cukrowej albo 30 ton bawełny. Jeśli nie odpowiesz lub odpowiesz nieprawidłowo Dżin zabiera ci 10 ton kakao i znika z towarem.', 'QUESTION', false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 8, 'BOTTLE', 'TEST', 'W butelce znajduje się potargany list o złowrogim tytule „Lista zakupów”. Niestety poza listą zakupów składników niezbędnych do wydania niedzielnego obiadu u Gubernatora Wyspy nie ma nic więcej! Zachowam go na później, może się do czegoś przyda…', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 8, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton wanilii.', null, false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 8, 'BOTTLE', 'TEST', 'W butelce znajduje się kwit depozytowy o wartości 10 sztuk złota na okaziciela, do zrealizowania w porcie do którego płyniecie! A to ci fart!', 'QUESTION', false);
