package pl.btsoftware.ship.game.country;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Country {
    JAMAICA("Jamaica"),
    HAITI("Haiti"),
    CUBA("Cuba"),
    GUATEMALA("Guatemala"),
    PUERTO_RICO("Puerto Rico");

    private final String name;

    public static Country from(String text) {
        for (Country b : Country.values()) {
            if (b.getName().equals(text)) {
                return b;
            }
        }
        return null;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
