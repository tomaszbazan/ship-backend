package pl.btsoftware.ship.registration.game;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.registration.game.exception.GameAlreadyExistsException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final EventsService eventsService;
    private final PasswordEncoder passwordEncoder;

    public Optional<GameEntity> findGame(GameName gameName) {
        return gameRepository.findByName(gameName);
    }

    GameName register(RegisterGameRequest registerGameRequest) {
        GameName gameName = GameName.from(registerGameRequest);
        verifyIfExists(gameName);

        GameEntity gameEntity = gameRepository.save(GameEntity.from(registerGameRequest, passwordEncoder));
        eventsService.copyAllToGame(gameEntity);

        return gameEntity.getName();
    }

    private void verifyIfExists(GameName gameName) {
        findGame(gameName).ifPresent(game -> {
            throw new GameAlreadyExistsException(gameName);
        });
    }
}
