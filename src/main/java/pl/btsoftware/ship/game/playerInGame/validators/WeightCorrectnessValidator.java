package pl.btsoftware.ship.game.playerInGame.validators;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.board.BoardInformationService;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.playerInGame.exception.MaximumWeightExceededException;

@Service
@AllArgsConstructor
public class WeightCorrectnessValidator {
    private static final int MAX_SHIP_WEIGHT = 1300;

    private final BoardInformationService boardInformationService;

    public void canMoveOn(PositionOnBoard newPosition, Integer shipWeight) {
        if (shipWeight > MAX_SHIP_WEIGHT) {
            throw new MaximumWeightExceededException();
        }
        int fieldMaximumWeight = boardInformationService.fieldMaximumWeight(newPosition);
        if (shipWeight > fieldMaximumWeight) {
            throw new MaximumWeightExceededException(fieldMaximumWeight);
        }
    }
}
