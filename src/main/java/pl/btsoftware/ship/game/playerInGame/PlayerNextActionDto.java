package pl.btsoftware.ship.game.playerInGame;

import pl.btsoftware.ship.game.events.ActionKind;
import pl.btsoftware.ship.game.events.EventsInGameEntity;
import pl.btsoftware.ship.game.events.SpecialFieldKind;

public record PlayerNextActionDto(String title, String description, ActionKind action, SpecialFieldKind type) {
    static PlayerNextActionDto from(EventsInGameEntity event) {
        if(event == null) {
            return null;
        }
        return new PlayerNextActionDto(event.getTitle(), event.getDescription(), event.getAction(), event.getType());
    }
}