create table event
(
    x           integer not null,
    y           integer not null,
    constraint events_pk
        primary key (y, x),
    type        varchar not null,
        constraint type_check
            check ( type in ('ADVENTURE', 'TREASURE', 'BOTTLE') ),
    title       varchar not null,
    description varchar not null,
    next_action      varchar,
        constraint next_action_check
            check ( next_action in ('QUESTION') ),
    removable   boolean not null
);

create table event_in_game
(
    x           integer not null,
        constraint x_check
            check ( x > 0 AND x < 9 ),
    y           integer not null,
        constraint y_check
            check ( y > 0 AND y < 10 ),
        constraint events_in_game_pk
            primary key (y, x),
    game_id uuid not null
        constraint event_in_game_game_fk
            references game,
    type        varchar not null,
        constraint type_check
            check ( type in ('ADVENTURE', 'TREASURE', 'BOTTLE') ),
    title       varchar not null,
    description varchar not null,
    next_action      varchar,
        constraint next_action_check
            check ( next_action in ('QUESTION') ),
    removable   boolean not null
);

create table event_reward
(
    id          uuid    not null
        constraint event_reward_pk
            primary key,
    x           integer not null,
        constraint x_check
            check ( x > 0 AND x < 9 ),
    y           integer not null,
        constraint y_check
            check ( y > 0 AND y < 10 ),
--     constraint reward_event_fk
--         foreign key (x, y) references event (x, y),
    goods_id    uuid
        constraint reward_goods_fk
            references goods (id),
    card_id    uuid
        constraint reward_card_fk
            references card (id),
    conjunction varchar not null,
        constraint conjunction_check
            check ( conjunction in ('AND', 'OR') ),
    type varchar not null
);

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
--
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 2, 'BOTTLE', 'TEST', 'W butelce znajduje się weksel In blanco. Jeśli staniesz na jakimś polu z innym statkiem, możesz przedstawić weksel do zapłaty – zabierasz 10 ton towaru który chcesz ale w zamian zostawiasz 10 ton czegoś, co ci tylko zajmuje miejsce w ładowni. Jeśli staniesz na jednym polu z innym statkiem ale nie skorzystasz z weksla, traci on swoją ważność i staje się bezużytecznym kawałkiem papieru.', 'BILL_OF_EXCHANGE', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 2, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton bawełny', 'GOODS', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 2, 'BOTTLE', 'TEST', 'Kapitan Kuternoga był ci winien trochę herbaty. Niniejszy list jest kwitem depozytowym na 40 ton herbaty do realizacji w porcie. Herbata ta nie wlicza się do wagi statku ani do realizacji zamówienia handlowego. Wlicza się do ogólnego wyniku finansowego na koniec gry po cenach oficjalnych, możesz tym kwitem handlować jak każdym innym dobrem.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 2, 'BOTTLE', 'TEST', 'GÓRSKIE POWIETRZE Z ANDÓW', null, false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 3, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 30 ton kakao.', 'GOODS', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 3, 'BOTTLE', 'TEST', 'W butelce znajduje się stary pergamin a na nim zaklęcie jasnowidzenia. WOW! Ekstra sprawa takie jasnowidzenie! Nie wiesz co to jest ale już to chcesz! Na wszelki wypadek każesz ewakuować załogę do szalup kiedy ty wypowiesz zaklęcie na głos. Huknęło, gruchnęło, pokład spowił dym jak po wybuchu beczki prochu ale ty widzisz wszystko wyraźnie jak nigdy! Sięgasz wzrokiem dużo dalej niż pozwala horyzont... (Sprawdź co kryje jedno, wybrane sąsiednie do waszej aktualnej pozycji pole. Załoga wraca na pokład a ty z miną admirała floty zarządzasz kierunek rejsu i ruszacie w dalszą podróż)', 'VISION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 3, 'BOTTLE', 'TEST', 'W butelce znajduje się weksel In blanco. Jeśli staniesz na jakimś polu z innym statkiem, możesz przedstawić weksel do zapłaty – zabierasz 10 ton towaru który chcesz ale w zamian zostawiasz 10 ton czegoś, co ci tylko zajmuje miejsce w ładowni. Jeśli staniesz na jednym polu z innym statkiem ale nie skorzystasz z weksla, traci on swoją ważność i staje się bezużytecznym kawałkiem papieru.', 'BILL_OF_EXCHANGE', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (1, 4, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton bawełny', 'GOODS', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 4, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton wanilii.', 'GOODS', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 5, 'BOTTLE', 'TEST', 'GÓRSKIE POWIETRZE Z ANDÓW', null, false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 5, 'BOTTLE', 'TEST', 'Znalazłeś butelkę w której siedział Dżin Dariusz. Jeśli odgadniesz jego zagadkę spełni jedno twoje życzenie – albo otrzymasz 30 ton kakao albo 30 ton kawy. Jeśli nie odpowiesz lub odpowiesz nieprawidłowo Dżin zabiera ci 10 ton tytoniu i znika z towarem.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 5, 'BOTTLE', 'TEST', 'W butelce znajduje się kwit depozytowy o wartości 10 sztuk złota na okaziciela, do zrealizowania w porcie do którego płyniecie! A to ci fart!', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (8, 5, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 30 ton kakao.', 'GOODS', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 6, 'BOTTLE', 'TEST', 'W butelce znajduje się potargany list o złowrogim tytule „Lista zakupów”. Niestety poza listą zakupów składników niezbędnych do wydania niedzielnego obiadu u Gubernatora Wyspy nie ma nic więcej! Zachowam go na później, może się do czegoś przyda...', null, false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 6, 'BOTTLE', 'TEST', 'W butelce znajduje się stary pergamin a na nim zaklęcie jasnowidzenia. WOW! Ekstra sprawa takie jasnowidzenie! Nie wiesz co to jest ale już to chcesz! Na wszelki wypadek każesz ewakuować załogę do szalup kiedy ty wypowiesz zaklęcie na głos. Huknęło, gruchnęło, pokład spowił dym jak po wybuchu beczki prochu ale ty widzisz wszystko wyraźnie jak nigdy! Sięgasz wzrokiem dużo dalej niż pozwala horyzont... (Sprawdź co kryje jedno, wybrane sąsiednie do waszej aktualnej pozycji pole. Załoga wraca na pokład a ty z miną admirała floty zarządzasz kierunek rejsu i ruszacie w dalszą podróż)', 'VISION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (4, 7, 'BOTTLE', 'TEST', 'W butelce znajduje się niedopita resztka jakiejś berbeluchy. Sądząc po smrodzie cieczy raczej nie kosztowała majątku. Na wszelki wypadek żeby nikt się nie zatruł wyrzucasz butelkę za burtę, zły że w ogóle zawracałeś sobie nią głowę! Czego spodziewałeś się znaleźć w pustej flaszce? Mapy ze skarbami piratów???', null, false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (6, 7, 'BOTTLE', 'TEST', 'Kapitan Drewniana Papuga był ci winien trochę bawełny. Niniejszy list jest kwitem depozytowym na 50 ton bawełny do realizacji w porcie. Bawełna ta nie wlicza się do wagi statku ale wlicza się do realizacji zamówienia handlowego w porcie i ogólnego wyniku finansowego na koniec gry po cenach oficjalnych, możesz tym kwitem handlować jak każdym innym dobrem.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (7, 7, 'BOTTLE', 'TEST', 'Znalazłeś butelkę w której siedział Dżin Wacław. Jeśli odgadniesz jego zagadkę spełni jedno twoje życzenie – albo otrzymasz 30 ton trzciny cukrowej albo 30 ton bawełny. Jeśli nie odpowiesz lub odpowiesz nieprawidłowo Dżin zabiera ci 10 ton kakao i znika z towarem.', 'QUESTION', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (2, 8, 'BOTTLE', 'TEST', 'W butelce znajduje się potargany list o złowrogim tytule „Lista zakupów”. Niestety poza listą zakupów składników niezbędnych do wydania niedzielnego obiadu u Gubernatora Wyspy nie ma nic więcej! Zachowam go na później, może się do czegoś przyda…', null, false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (3, 8, 'BOTTLE', 'TEST', 'Znalazłeś butelkę a w środku było 10 ton wanilii.', 'GOODS', false);
-- INSERT INTO event (x, y, type, title, description, next_action, removable) VALUES (5, 8, 'BOTTLE', 'TEST', 'W butelce znajduje się kwit depozytowy o wartości 10 sztuk złota na okaziciela, do zrealizowania w porcie do którego płyniecie! A to ci fart!', 'QUESTION', false);
