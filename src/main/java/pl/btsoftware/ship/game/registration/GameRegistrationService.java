package pl.btsoftware.ship.game.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GameRegistrationService {

    private final GameRepository gameRepository;

    GameName register(GameRequest gameRequest) {
        Optional<GameEntity> createdGame = findGame(new GameName(gameRequest.getGameName()));
        if (createdGame.isPresent()) {
            throw new GameAlreadyExistsException(createdGame.get().getName());
        }

        GameEntity gameEntity = gameRepository.save(new GameEntity(gameRequest));

        return gameEntity.getName();
    }

    Optional<GameEntity> findGame(GameName gameName) {
        return gameRepository.findByName(gameName);

    }
}
