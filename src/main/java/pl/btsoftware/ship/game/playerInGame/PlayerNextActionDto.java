package pl.btsoftware.ship.game.playerInGame;

import pl.btsoftware.ship.game.events.NextActionKind;
import pl.btsoftware.ship.game.events.SpecialFieldKind;

public record PlayerNextActionDto(String title, String description, NextActionKind action, SpecialFieldKind type) {
}
