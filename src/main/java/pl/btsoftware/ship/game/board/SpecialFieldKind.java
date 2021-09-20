package pl.btsoftware.ship.game.board;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SpecialFieldKind {
    BOTTLE("bottle"), ADVENTURE("adventure"), TREASURE("treasure");

    private final String kind;

    @JsonValue
    public String getKind() {
        return kind;
    }
}
