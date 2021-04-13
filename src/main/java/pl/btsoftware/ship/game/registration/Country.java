package pl.btsoftware.ship.game.registration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Country {
    JAMAICA("Jamaica"),
    HAITI("Haiti"),
    CUBA("Cuba"),
    GUATEMALA("Guatemala"),
    PUERTO_RICO("Puerto Rico");

    private final String countryName;

    @JsonValue
    public String getCountryName() {
        return countryName;
    }
}
