package pl.btsoftware.ship.game.country;

import pl.btsoftware.ship.game.board.PlayerPosition;

import static pl.btsoftware.ship.game.country.Country.*;

public class CountryFinder {
    public static Country nextFreeCountry(long numberOfPlayersInGame) {
        switch ((int) numberOfPlayersInGame) {
            case 0:
                return JAMAICA;
            case 1:
                return HAITI;
            case 2:
                return CUBA;
            case 3:
                return GUATEMALA;
            case 4:
                return PUERTO_RICO;
            default:
                throw new PlayerPosition.MaximumPlayersInGameException();
        }
    }
}
