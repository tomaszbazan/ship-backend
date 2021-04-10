package pl.btsoftware.ship.game.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static pl.btsoftware.ship.game.registration.Country.*;

@Service
@AllArgsConstructor
class CountryFinderService {
    private final PlayerInGameRepository playerInGameRepository;

    Country findNextFreeCountry(GameName gameName) {
        long numberOfPlayersInGame = playerInGameRepository.countByGame_Name(gameName);
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
                throw new MaximumPlayersInGameException();
        }
    }
}
