package pl.btsoftware.ship.game.events;

import static pl.btsoftware.ship.game.events.SpecialFieldKind.BOTTLE;

public record EventSnapshot(EventDescription eventDescription) {
    public record EventDescription(String title, String description, NextActionKind action, SpecialFieldKind type) {}

    public static EventSnapshot from(EventInGameEntity event) {
        return new EventSnapshot(new EventDescription(event.getTitle(), event.getDescription(), event.getNextAction(), event.getType()));
    }

    public EventDescription description() {
        if(foundBottle()) {
            return new EventDescription(null, null, null, BOTTLE);
        } else {
            return eventDescription;
        }
    }

    public boolean foundBottle() {
        return eventDescription.type() != null && eventDescription.type().equals(BOTTLE);
    }
}
