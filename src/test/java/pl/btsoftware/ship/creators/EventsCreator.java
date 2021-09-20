package pl.btsoftware.ship.creators;

import pl.btsoftware.ship.game.events.Events;
import pl.btsoftware.ship.game.events.FieldId;
import pl.btsoftware.ship.game.events.SpecialFieldKind;

import java.util.List;

public class EventsCreator {
    public static List<Events> defaultEvents() {
        return List.of(bottle(2, 3, "first", "first"), bottle(4, 8, "second", "second"), bottle(5, 6, "third", "third"));
    }

    private static Events bottle(int x, int y, String title, String description) {
        return new Events(new FieldId(x, y), SpecialFieldKind.BOTTLE, title, description, null, true);
    }
}
