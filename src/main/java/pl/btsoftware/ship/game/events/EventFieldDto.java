package pl.btsoftware.ship.game.events;

import pl.btsoftware.ship.shared.PositionOnBoard;

public record EventFieldDto(PositionOnBoard position, SpecialFieldKind kind) {
}
