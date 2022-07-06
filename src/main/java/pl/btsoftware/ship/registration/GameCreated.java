package pl.btsoftware.ship.registration;

import pl.btsoftware.ship.shared.GameName;

public record GameCreated(GameName game, Integer roundTime) {
}
