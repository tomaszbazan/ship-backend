package pl.btsoftware.ship.registration;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.registration.exception.GameAlreadyExistsException;
import pl.btsoftware.ship.shared.GameName;

import java.util.Optional;

@Service
@AllArgsConstructor
class GameService {
    private final GameRepository gameRepository;
    private final EventsService eventsService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher events;

    public Optional<GameEntity> findGame(GameName game) {
        return gameRepository.findByName(game);
    }

    GameName register(RegisterGameRestController.RegisterGameRequest registerGameRequest) {
        verifyIfExists(registerGameRequest.game());

        GameEntity gameEntity = gameRepository.save(GameEntity.from(registerGameRequest, passwordEncoder));
        eventsService.copyAllToGame(gameEntity.gameName());
        events.publishEvent(new GameCreated(gameEntity.gameName(), registerGameRequest.roundTime()));

        return gameEntity.gameName();
    }

    private void verifyIfExists(GameName gameName) {
        findGame(gameName).ifPresent(game -> {
            throw new GameAlreadyExistsException(gameName);
        });
    }
}
