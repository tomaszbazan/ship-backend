package pl.btsoftware.ship.game.events;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NextActionKind {
    QUESTION("question"), DICE("dice"), NEXT_MOVE("next_move"), VISION("vision"), SICK("sick");

    private final String action;

    @JsonValue
    public String getAction() {
        return action;
    }
}
