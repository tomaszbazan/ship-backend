package pl.btsoftware.ship.game.events;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActionKind {
    QUESTION("question");

    private final String kind;

    @JsonValue
    public String getKind() {
        return kind;
    }
}
