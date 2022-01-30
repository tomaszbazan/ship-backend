package pl.btsoftware.ship.creators;

import pl.btsoftware.ship.game.events.EventEntity;
import pl.btsoftware.ship.game.events.FieldId;
import pl.btsoftware.ship.game.events.NextActionKind;
import pl.btsoftware.ship.game.events.SpecialFieldKind;
import pl.btsoftware.ship.game.goods.GoodsEntity;

import java.util.List;

public class EventsCreator {
    public static List<EventEntity> defaultEvents() {
        return List.of(bottle(2, 3, "first", "first"), bottle(4, 8, "second", "second"), bottle(5, 6, "third", "third"));
    }

    public static EventEntity bottle(List<GoodsEntity> goods) {
        return new EventEntity(new FieldId(1, 2), SpecialFieldKind.BOTTLE, "any", "any", NextActionKind.DICE, true);
    }

    public static EventEntity bottle(int x, int y, String title, String description) {
        return new EventEntity(new FieldId(x, y), SpecialFieldKind.BOTTLE, title, description, NextActionKind.DICE, true);
    }
}
