package pl.btsoftware.ship.game.state;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.playerPosition.exception.GameNotFoundException;
import pl.btsoftware.ship.game.state.exception.CantStartGameNotInPreparingStateException;
import pl.btsoftware.ship.registration.GameCreated;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.Round;

@Service
@AllArgsConstructor
public class GameStateService {
    private final GameStateRepository gameStateRepository;

    public boolean canJoinToGame(GameName gameName) {
        return gameStateRepository.findById(gameName).map(GameStateEntity::isPreparing).orElse(false);
    }

    @EventListener
    public void handleGameCreated(GameCreated event) {
        gameStateRepository.save(GameStateEntity.register(event.game()));
    }

    void startGame(GameName game) {
        GameStateEntity state = gameStateRepository.findById(game).orElseThrow(() -> new GameNotFoundException(game));
        if (!state.isPreparing()) {
            throw new CantStartGameNotInPreparingStateException(game);
        }

        state.nextState();
        gameStateRepository.save(state);
    }

    void nextRound(GameName game) {
        GameStateEntity state = gameStateRepository.findById(game).orElseThrow(() -> new GameNotFoundException(game));
        state.nextState();
        gameStateRepository.save(state);
    }

    public Round state(GameName game) {
        return gameStateRepository.findById(game).orElseThrow(() -> new GameNotFoundException(game)).getRound();
    }
}
