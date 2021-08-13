package pl.btsoftware.ship.registration.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.registration.game.exception.GameAlreadyExistsException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Optional<GameEntity> findGame(GameName gameName) {
        return gameRepository.findByName(gameName);
    }

    GameName register(RegisterGameRequest registerGameRequest) {
        GameName gameName = GameName.from(registerGameRequest);
        verifyIfExists(gameName);

        GameEntity gameEntity = gameRepository.save(GameEntity.from(registerGameRequest));

        return gameEntity.getName();
    }

    private void verifyIfExists(GameName gameName) {
        findGame(gameName).ifPresent(game -> {
            throw new GameAlreadyExistsException(gameName);
        });
    }
}
