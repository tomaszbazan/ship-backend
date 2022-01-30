package pl.btsoftware.ship.game.events;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Conjunction {
    AND("AND"), OR("OR");

    private final String conjunction;

    @JsonValue
    public String getConjunction() {
        return conjunction;
    }
}
