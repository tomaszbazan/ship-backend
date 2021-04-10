package pl.btsoftware.ship.game.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameJoinerService {
    private final PlayerRepository playerRepository;
    private final PlayerInGameRepository playerInGameRepository;
    private final GameRegistrationService gameRegistrationService;
    private final CountryFinderService countryFinderService;

    @Transactional
    PlayerJoined joinPlayer(GameName gameName, JoinRequest joinRequest) {
        Optional<GameEntity> gameEntity = gameRegistrationService.findGame(gameName);
        if (!gameEntity.isPresent()) {
            throw new GameNotExistsException(gameName);
        }
        PlayerEntity player = playerRepository.save(new PlayerEntity(joinRequest));
        Country country = countryFinderService.findNextFreeCountry(gameName);
        PlayerInGameEntity playerInGame = playerInGameRepository.save(new PlayerInGameEntity(player, gameEntity.get(), country));
        return new PlayerJoined(playerInGame);
    }
}
