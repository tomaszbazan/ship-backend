package pl.btsoftware.ship.game.board;

import lombok.Value;

@Value
class SpecialField {
    SpecialFieldKind kind;
    int number;
}
