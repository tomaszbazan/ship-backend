package pl.btsoftware.ship.creators;

import pl.btsoftware.ship.game.events.CardEntity;
import pl.btsoftware.ship.game.events.CardType;
import pl.btsoftware.ship.game.events.Conjunction;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CardsCreator {
    public static List<CardEntity> cardsEntity(CardType cardType) {
        return Collections.singletonList(new CardEntity(UUID.randomUUID(), cardType, Conjunction.AND, null, null));
    }
}
