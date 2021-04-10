package pl.btsoftware.ship.game.registration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
enum Country {
    JAMAICA("Jamaica"),
    HAITI("Haiti"),
    CUBA("Cuba"),
    GUATEMALA("Guatemala"),
    PUERTO_RICO("Puerto Rico");

    private final String countryName;

}
