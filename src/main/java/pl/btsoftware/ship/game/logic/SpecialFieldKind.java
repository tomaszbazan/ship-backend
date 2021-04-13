package pl.btsoftware.ship.game.logic;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
enum SpecialFieldKind {
    BOTTLE("bottle"), ADVENTURE("adventure"), TREASURE("treasure");

    private final String kind;

    @JsonValue
    public String getKind() {
        return kind;
    }
}
