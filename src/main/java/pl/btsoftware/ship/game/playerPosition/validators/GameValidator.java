package pl.btsoftware.ship.game.playerPosition.validators;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.playerPosition.exception.CantMoveInFinishedStateException;
import pl.btsoftware.ship.game.playerPosition.exception.CantMoveInPreparingStateException;
import pl.btsoftware.ship.game.state.GameStateService;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.Round;

@Service
@AllArgsConstructor
public
class GameValidator {
    private final GameStateService gameStateService;

    public void canMove(GameName game) {
        if(gameStateService.state(game) == Round.PREPARING) {
            throw new CantMoveInPreparingStateException();
        }

        if(gameStateService.state(game) == Round.FINISHED) {
            throw new CantMoveInFinishedStateException();
        }
    }
}
