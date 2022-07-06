CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 2, 'ADVENTURE', 'TEST', 'Znalazłeś bardzo ciekawy okaz muszelki, która należała do niezwykle rzadko spotykanego ślimaka morskiego Strombus Gigas.
W porcie będziesz mógł za nią dostać 5 sztabek złota. Jeśli dodatkowo już teraz poprawnie odpowiesz na zagadkę, będziesz mógł wliczyć do swojego bilansu na koniec gry 10 ton herbaty i 20 ton kawy (wliczają się do realizacji zamówienia handlowego, nie wliczają do ładowności statku).', 'QUESTION', false);
INSERT INTO event_reward (id, x, y) VALUES ('319d495a-d6f3-4575-a0e1-ed90c02d81bf', 4, 2);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'SNAIL', '319d495a-d6f3-4575-a0e1-ed90c02d81bf');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 3, 'ADVENTURE', 'TEST', 'Na zachrypniętą papugę!
Zza zakrętu wyłonił się szkuner piracki Kapitana Moczymordy! Nie pozostaje nic innego jak rzucić kośćmi.
1 lub 2: Kapitan Moczymorda to twój dawny druh i kompan, ucieszył się na wasz widok ale musi płynąć dalej i ścigać innych frajerów – mijacie się serdecznie i każdy płynie w swoją stronę
3 lub 4: Kapitan Moczymorda to twój dawny druh i kompan, ucieszył się na wasz widok, tym bardziej, że byłeś mu winny trochę złota! Z wielkim żalem ale jednak wobec przewagi liczebnej armat Kapitana oddajesz połowę swojego złota w gotówce
5 lub 6: Kapitan Moczymorda ucieszył się na wasz widok, tym bardziej, że ma trochę wolnego miejsca w swoich ładowniach. Wobec przewagi oddajesz bez walki Kapitanowi Moczymordzie 10 ton trzciny lub cytrusów', 'DICE', false);
-- TODO

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 3, 'ADVENTURE', 'TEST', 'Fortuna wam sprzyja – ciepły prąd zatokowy El Ninio, pojawiający się na tutejszych wodach raz na cztery lata właśnie się pojawił! Dzięki niemu zyskujecie trochę czasu, gdyż z siłą wodospadu ponosi was akurat w tym kierunku, w którym chcecie płynąć! Zatem możecie teraz wykonać dodatkowy ruch.', 'NEXT_MOVE', false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 3, 'ADVENTURE', 'TEST', 'Rum, który otrzymałeś w zaprzyjaźnionej acz nielegalnej gorzelni nieźle daje niezłego kopa! Postanawiasz ukryć go przed załogą i zachować na później. Ty zadecydujesz kiedy opróżnicie baryłki niezwykłego trunku. Dzięki temu załoga dostanie nowej energii i będzie się uwijać dwa razy szybciej niż normalnie a przez to w jednej turze pokonacie odległość dwóch pól – zgłoś ten fakt przed wykonaniem ruchu, w dowolnym momencie rejsu (nie dotyczy was ewentualna zawartość pola przez które tylko przepływacie, oczywiście poza głębokością toru wodnego)', 'QUESTION', false);
INSERT INTO event_reward (id, x, y) VALUES ('81390702-ebfe-49bc-9e77-df062a33c40b', 8, 3);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'RUM', '81390702-ebfe-49bc-9e77-df062a33c40b');

-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 4, 'ADVENTURE', 'TEST', 'A niech to mewa kopnie! Tylko tego brakowało, żeby rozpętała się tropikalna burza skoro do portu jest już tak blisko! Mimo zażartej walki z żywiołem o zachowanie życia i towaru, tracisz część cennego ładunku – możesz wybrać czego tracisz 10 ton: kawy, trzciny cukrowej czy cytrusów. Ładunek wypada za burtę i tyle go widzieli.', 'QUESTION', false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 4, 'ADVENTURE', 'TEST', 'Wyłowiłeś skrzynię pełną skarbów! Oprócz prawie nieużywanych kalesonów, kapeluszy i opasek na oko pirata, znalazłeś w środku 10 ton wanilii.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('3ffef3cb-0d6c-4a89-a8ed-6f59ef6da182', 4, 8);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'VANILLA', 10, '3ffef3cb-0d6c-4a89-a8ed-6f59ef6da182');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 5, 'ADVENTURE', 'TEST', 'Rum, który otrzymałeś w zaprzyjaźnionej acz nielegalnej gorzelni nieźle daje niezłego kopa! Postanawiasz ukryć go przed załogą i zachować na później. Ty zadecydujesz kiedy opróżnicie baryłki niezwykłego trunku. Dzięki temu załoga dostanie nowej energii i będzie się uwijać dwa razy szybciej niż normalnie a przez to w jednej turze pokonacie odległość dwóch pól – zgłoś ten fakt przed wykonaniem ruchu, w dowolnym momencie rejsu. (nie dotyczy was ewentualna zawartość pola przez które tylko przepływacie, oczywiście poza głębokością toru wodnego)', 'QUESTION', false);
INSERT INTO event_reward (id, x, y) VALUES ('88160fc3-b516-4985-814a-23f656231124', 2, 5);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'RUM', '88160fc3-b516-4985-814a-23f656231124');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 5, 'ADVENTURE', 'TEST', 'Znalazłeś bardzo ciekawy okaz muszelki, która należała do niezwykle rzadko spotykanego ślimaka morskiego Strombus Gigas. W porcie będziesz mógł za nią dostać 5 sztabek złota. Jeśli dodatkowo już teraz poprawnie odpowiesz na zagadkę, będziesz mógł wliczyć do swojego bilansu na koniec gry 10 ton herbaty i 20 ton kawy (wliczają się do realizacji zamówienia handlowego, nie wliczają do ładowności statku).', 'QUESTION', false);
INSERT INTO event_reward (id, x, y) VALUES ('86ca8344-01cf-473f-810c-5807e47ef085', 6, 5);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'SNAIL', '86ca8344-01cf-473f-810c-5807e47ef085');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 6, 'ADVENTURE', 'TEST', 'Na zachrypniętą papugę!
Zza zakrętu wyłonił się szkuner piracki Kapitana Moczymordy! Nie pozostaje nic innego jak rzucić kośćmi
1 lub 2: Kapitan Moczymorda to twój dawny druh i kompan, ucieszył się na wasz widok ale musi płynąć dalej i ścigać innych frajerów – mijacie się serdecznie i każdy płynie w swoją stronę
3 lub 4: Kapitan Moczymorda to twój dawny druh i kompan, ucieszył się na wasz widok, tym bardziej, że byłeś mu winny trochę złota! Z wielkim żalem ale jednak wobec przewagi liczebnej armat Kapitana oddajesz połowę swojego złota w gotówce
5 lub 6: Kapitan Moczymorda ucieszył się na wasz widok, tym bardziej, że ma trochę wolnego miejsca w swoich ładowniach. Wobec przewagi oddajesz bez walki Kapitanowi Moczymordzie 10 ton trzciny lub cytrusów).', 'DICE', false);
-- TODO

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 7, 'ADVENTURE', 'TEST', 'Oto na horyzoncie zamajaczyły jakieś przedmioty w wodzie. Po dopłynięciu okazało się, że jakiś inny statek miał pecha bo zmiotło mu z pokładu w czasie ostatniej burzy trochę ładunku. Po wyłowieniu okazało się, że uzbieraliście w ten sposób 60 ton kawy i 20 ton trzciny cukrowej. Udane połowy! ', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('3d97e712-b1fb-41dc-8bdb-0749e3a5909a', 2, 7);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'COFFEE', 60, '3d97e712-b1fb-41dc-8bdb-0749e3a5909a');
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 20, '3d97e712-b1fb-41dc-8bdb-0749e3a5909a');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 7, 'ADVENTURE', 'TEST', 'Fortuna wam sprzyja – ciepły prąd zatokowy El Ninio, pojawiający się na tutejszych wodach raz na cztery lata właśnie się pojawił! Dzięki niemu zyskujecie trochę czasu, gdyż z siłą wodospadu ponosi was akurat w tym kierunku, w którym chcecie płynąć! Zatem możecie teraz wykonać dodatkowy ruch.', 'NEXT_MOVE', false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 8, 'ADVENTURE', 'TEST', 'Wyłowiłeś skrzynię do połowy pełną skarbów! Oprócz zapasu drewnianych nóg, znalazłeś w środku 30 ton tytoniu.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('ec207bdd-0a98-4fca-8233-6b9825ae26ff', 4, 8);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'TOBACCO', 30, 'ec207bdd-0a98-4fca-8233-6b9825ae26ff');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 8, 'ADVENTURE', 'TEST', 'Oto na horyzoncie zamajaczyły jakieś przedmioty w wodzie. Po dopłynięciu okazało się, że jakiś inny statek miał pecha bo zmiotło mu z pokładu w czasie ostatniej burzy trochę ładunku. Po wyłowieniu okazało się, że uzbieraliście w ten sposób 30 ton tytoniu, 10 ton wanilii, 30 ton kakao i 10 ton trzciny cukrowej. Udane połowy!', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('e70b82c2-a79a-44b2-9d17-b62f83d64224', 6, 8);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'TOBACCO', 30, 'e70b82c2-a79a-44b2-9d17-b62f83d64224');
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'VANILLA', 10, 'e70b82c2-a79a-44b2-9d17-b62f83d64224');
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'COCOA', 30, 'e70b82c2-a79a-44b2-9d17-b62f83d64224');
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'SUGARCANE', 10, 'e70b82c2-a79a-44b2-9d17-b62f83d64224');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 8, 'ADVENTURE', 'TEST', 'Cała załoga zatruła się nieświeżymi rybami – wymiotom i biegunkom nie ma końca! W związku z tym nieszczęśliwym incydentem tracicie na kotwicy jeden dzień (jedną turą). (jeśli znasz recepturę lekarstwa na zatrucie pokarmowe możesz wyleczyć załogę i uniknąć straty czasu)', 'SICK', false);
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 9, 'ADVENTURE', 'TEST', 'Cała załoga zatruła się nieświeżymi rybami – wymiotom i biegunkom nie ma końca! W związku z tym nieszczęśliwym incydentem tracicie na kotwicy jeden dzień (jedną turą). (jeśli znasz recepturę lekarstwa na zatrucie pokarmowe możesz wyleczyć załogę i uniknąć straty czasu)', 'SICK', false);
--
INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 4, 'TREASURE', 'TEST', 'Znalazłeś maść z nietoperza – lekarstwo na wszelkie choroby.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('194b3afb-be1d-4279-ae08-7d977213cce8', 3, 4);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'CURE', '194b3afb-be1d-4279-ae08-7d977213cce8');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 4, 'TREASURE', 'TEST', 'Znalazłeś maść z nietoperza – lekarstwo na wszelkie choroby.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('a2be024f-dee0-4160-9339-f5ac9ec63e2a', 8, 4);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'CURE', 'a2be024f-dee0-4160-9339-f5ac9ec63e2a');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 5, 'TREASURE', 'TEST', 'Skrzynia pełna złota a dokładnie 50 sztabek.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('4f63aa3e-ce36-4443-a452-ff4299c55cf5', 5, 5);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'GOLD', 50, '4f63aa3e-ce36-4443-a452-ff4299c55cf5');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (1, 6, 'TREASURE', 'TEST', 'Zwrot podatku z urzędu podatków, ceł i opłat – otrzymujesz natychmiast 20 ton cytrusów.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('620c15b8-8861-423e-af07-c6cc11dd78de', 1, 6);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'CITRUS', 20, '620c15b8-8861-423e-af07-c6cc11dd78de');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 6, 'TREASURE', 'TEST', 'Skrzynia pełna złota a dokładnie 50 sztabek.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('be1eea3e-d106-4169-817b-9432716e1287', 4, 6);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'GOLD', 50, 'be1eea3e-d106-4169-817b-9432716e1287');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 6, 'TREASURE', 'TEST', 'JOKER – w porcie masz prawo zamienić JOKERA na 10 ton dowolnego towaru, który wliczy ci się do realizacji zamówienia handlowego
JOKER nie wlicza się do ładowności, może być przedmiotem handlu (zanim wpłyniesz do portu) na dowolnych zasadach', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('81e83093-1c3d-40c7-abb5-4cac0676c6db', 8, 6);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'JOKER', '81e83093-1c3d-40c7-abb5-4cac0676c6db');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 2, 'BOTTLE', 'TEST', 'W butelce znajduje się weksel In blanco. Jeśli staniesz na jakimś polu z innym statkiem, możesz przedstawić weksel do zapłaty – zabierasz 10 ton towaru który chcesz ale w zamian zostawiasz 10 ton czegoś, co ci tylko zajmuje miejsce w ładowni. Jeśli staniesz na jednym polu z innym statkiem ale nie skorzystasz z weksla, traci on swoją ważność i staje się bezużytecznym kawałkiem papieru.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('d0cc1509-4c2c-4e5c-9e26-08dc70ce2569', 2, 2);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'EXCHANGE', 'd0cc1509-4c2c-4e5c-9e26-08dc70ce2569');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 2, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton bawełny', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('bfcde53e-876b-40e3-b037-4b0150abe209', 3, 2);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'COTTON', 10, 'bfcde53e-876b-40e3-b037-4b0150abe209');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 2, 'BOTTLE', 'TEST', 'Kapitan Kuternoga był ci winien trochę herbaty. Niniejszy list jest kwitem depozytowym na 40 ton herbaty do realizacji w porcie. Herbata ta nie wlicza się do wagi statku ani do realizacji zamówienia handlowego. Wlicza się do ogólnego wyniku finansowego na koniec gry po cenach oficjalnych, możesz tym kwitem handlować jak każdym innym dobrem.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('c83493e5-f41f-48df-84e7-a155fc32d5de', 6, 2);
INSERT INTO event_card (id, type, event_reward_id) VALUES ('f679c292-b6ea-4443-a298-a18ba4dbb9bf', 'DEPOSIT', 'c83493e5-f41f-48df-84e7-a155fc32d5de'); -- TODO: 40t tea
INSERT INTO event_goods (id, type, amount, card_id) VALUES (uuid_generate_v4(), 'TEA', 40, 'f679c292-b6ea-4443-a298-a18ba4dbb9bf');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 2, 'BOTTLE', 'TEST', 'GÓRSKIE POWIETRZE Z ANDÓW', null, false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 3, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 30 ton kakao.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('ce1e4ca7-8331-4744-9cd1-880e284df26d', 3, 3);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'COCOA', 30, 'ce1e4ca7-8331-4744-9cd1-880e284df26d');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 3, 'BOTTLE', 'TEST', 'W butelce znajduje się stary pergamin a na nim zaklęcie jasnowidzenia. WOW! Ekstra sprawa takie jasnowidzenie! Nie wiesz co to jest ale już to chcesz! Na wszelki wypadek każesz ewakuować załogę do szalup kiedy ty wypowiesz zaklęcie na głos. Huknęło, gruchnęło, pokład spowił dym jak po wybuchu beczki prochu ale ty widzisz wszystko wyraźnie jak nigdy! Sięgasz wzrokiem dużo dalej niż pozwala horyzont... (Sprawdź co kryje jedno, wybrane sąsiednie do waszej aktualnej pozycji pole. Załoga wraca na pokład a ty z miną admirała floty zarządzasz kierunek rejsu i ruszacie w dalszą podróż)', 'VISION', false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 3, 'BOTTLE', 'TEST', 'W butelce znajduje się weksel In blanco. Jeśli staniesz na jakimś polu z innym statkiem, możesz przedstawić weksel do zapłaty – zabierasz 10 ton towaru który chcesz ale w zamian zostawiasz 10 ton czegoś, co ci tylko zajmuje miejsce w ładowni. Jeśli staniesz na jednym polu z innym statkiem ale nie skorzystasz z weksla, traci on swoją ważność i staje się bezużytecznym kawałkiem papieru.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('e42bf748-7986-4bb8-b26f-d2abb0c66cd8', 6, 3);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'EXCHANGE', 'e42bf748-7986-4bb8-b26f-d2abb0c66cd8');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (1, 4, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton bawełny', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('980d0f2e-2b96-4eb0-981d-773fcc252943', 1, 4);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'COTTON', 10, '980d0f2e-2b96-4eb0-981d-773fcc252943');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 4, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton wanilii.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('3cd12879-cc5a-42c2-87ae-e70b08cd4b4e', 7, 4);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'VANILLA', 10, '3cd12879-cc5a-42c2-87ae-e70b08cd4b4e');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 5, 'BOTTLE', 'TEST', 'Górskie powietrze z Andów', null, false);

-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 5, 'BOTTLE', 'TEST', 'Znalazłeś butelkę w której siedział Dżin Dariusz. Jeśli odgadniesz jego zagadkę spełni jedno twoje życzenie – albo otrzymasz 30 ton kakao albo 30 ton kawy. Jeśli nie odpowiesz lub odpowiesz nieprawidłowo Dżin zabiera ci 10 ton tytoniu i znika z towarem.', 'QUESTION', false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 5, 'BOTTLE', 'TEST', 'W butelce znajduje się kwit depozytowy o wartości 10 sztuk złota na okaziciela, do zrealizowania w porcie do którego płyniecie! A to ci fart!', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('aa6c1d81-c409-4857-ae3e-1e70bbf77087', 7, 5);
INSERT INTO event_card (id, type, event_reward_id) VALUES ('c4835a7a-52dd-411f-b7c8-804b6a2e0e77', 'DEPOSIT', 'aa6c1d81-c409-4857-ae3e-1e70bbf77087');
INSERT INTO event_goods (id, type, amount, card_id) VALUES (uuid_generate_v4(), 'GOLD', 10, 'c4835a7a-52dd-411f-b7c8-804b6a2e0e77');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 5, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 30 ton kakao.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('ac933d91-85ea-498d-a9ee-0674709123a9', 8, 5);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'COCOA', 30, 'ac933d91-85ea-498d-a9ee-0674709123a9');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 6, 'BOTTLE', 'TEST', 'W butelce znajduje się potargany list o złowrogim tytule „Lista zakupów”. Niestety poza listą zakupów składników niezbędnych do wydania niedzielnego obiadu u Gubernatora Wyspy nie ma nic więcej! Zachowam go na później, może się do czegoś przyda...', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('de575238-55a2-479b-ba2e-1d9f40acc815', 3, 6);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'SHOPPING_LIST', 'de575238-55a2-479b-ba2e-1d9f40acc815');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 6, 'BOTTLE', 'TEST', 'W butelce znajduje się stary pergamin a na nim zaklęcie jasnowidzenia. WOW! Ekstra sprawa takie jasnowidzenie! Nie wiesz co to jest ale już to chcesz! Na wszelki wypadek każesz ewakuować załogę do szalup kiedy ty wypowiesz zaklęcie na głos. Huknęło, gruchnęło, pokład spowił dym jak po wybuchu beczki prochu ale ty widzisz wszystko wyraźnie jak nigdy! Sięgasz wzrokiem dużo dalej niż pozwala horyzont... (Sprawdź co kryje jedno, wybrane sąsiednie do waszej aktualnej pozycji pole. Załoga wraca na pokład a ty z miną admirała floty zarządzasz kierunek rejsu i ruszacie w dalszą podróż)', 'VISION', false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 7, 'BOTTLE', 'TEST', 'W butelce znajduje się niedopita resztka jakiejś berbeluchy. Sądząc po smrodzie cieczy raczej nie kosztowała majątku. Na wszelki wypadek żeby nikt się nie zatruł wyrzucasz butelkę za burtę, zły że w ogóle zawracałeś sobie nią głowę! Czego spodziewałeś się znaleźć w pustej flaszce? Mapy ze skarbami piratów???', null, false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 7, 'BOTTLE', 'TEST', 'Kapitan Drewniana Papuga był ci winien trochę bawełny. Niniejszy list jest kwitem depozytowym na 50 ton bawełny do realizacji w porcie. Bawełna ta nie wlicza się do wagi statku ale wlicza się do realizacji zamówienia handlowego w porcie i ogólnego wyniku finansowego na koniec gry po cenach oficjalnych, możesz tym kwitem handlować jak każdym innym dobrem.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('bf25f302-d65a-40ec-a8ab-2df7d4abbe3b', 6, 7);
INSERT INTO event_card (id, type, event_reward_id) VALUES ('2c679c99-2afa-4c58-a530-78b1cc0fd70a', 'DEPOSIT', 'bf25f302-d65a-40ec-a8ab-2df7d4abbe3b');
INSERT INTO event_goods (id, type, amount, card_id) VALUES (uuid_generate_v4(), 'COTTON', 50, '2c679c99-2afa-4c58-a530-78b1cc0fd70a');

-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 7, 'BOTTLE', 'TEST', 'Znalazłeś butelkę w której siedział Dżin Wacław. Jeśli odgadniesz jego zagadkę spełni jedno twoje życzenie – albo otrzymasz 30 ton trzciny cukrowej albo 30 ton bawełny. Jeśli nie odpowiesz lub odpowiesz nieprawidłowo Dżin zabiera ci 10 ton kakao i znika z towarem.', 'QUESTION', false);

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 8, 'BOTTLE', 'TEST', 'W butelce znajduje się potargany list o złowrogim tytule „Lista zakupów”. Niestety poza listą zakupów składników niezbędnych do wydania niedzielnego obiadu u Gubernatora Wyspy nie ma nic więcej! Zachowam go na później, może się do czegoś przyda…', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('bb2f2424-81db-45a6-881b-e7ef4ad30a7f', 2, 8);
INSERT INTO event_card (id, type, event_reward_id) VALUES (uuid_generate_v4(), 'SHOPPING_LIST', 'bb2f2424-81db-45a6-881b-e7ef4ad30a7f');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 8, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton wanilii.', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('8de10f1c-03d1-40a8-8773-13821c6ede25', 3, 8);
INSERT INTO event_goods (id, type, amount, event_reward_id) VALUES (uuid_generate_v4(), 'VANILLA', 10, '8de10f1c-03d1-40a8-8773-13821c6ede25');

INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 8, 'BOTTLE', 'TEST', 'W butelce znajduje się kwit depozytowy o wartości 10 sztuk złota na okaziciela, do zrealizowania w porcie do którego płyniecie! A to ci fart!', null, false);
INSERT INTO event_reward (id, x, y) VALUES ('2aa3be09-9fcb-4795-b9bf-cf9e5d9a28bd', 5, 8);
INSERT INTO event_card (id, type, event_reward_id) VALUES ('e269f84e-2b0c-4f5c-985b-a84dd0a4f5f4', 'DEPOSIT', '2aa3be09-9fcb-4795-b9bf-cf9e5d9a28bd');
INSERT INTO event_goods (id, type, amount, card_id) VALUES (uuid_generate_v4(), 'GOLD', 10, 'e269f84e-2b0c-4f5c-985b-a84dd0a4f5f4');
