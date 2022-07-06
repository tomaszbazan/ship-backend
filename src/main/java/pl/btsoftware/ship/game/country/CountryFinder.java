package pl.btsoftware.ship.game.country;

import pl.btsoftware.ship.game.playerPosition.exception.MaximumPlayersInGameException;

import static pl.btsoftware.ship.game.country.Country.*;

public class CountryFinder {
    private CountryFinder() {
        throw new IllegalStateException("Utility class");
    }
    public static Country nextFreeCountry(long numberOfPlayersInGame) {
        return switch ((int) numberOfPlayersInGame) {
            case 0 -> JAMAICA;
            case 1 -> HAITI;
            case 2 -> CUBA;
            case 3 -> GUATEMALA;
            case 4 -> PUERTO_RICO;
            default -> throw new MaximumPlayersInGameException();
        };
    }
}
