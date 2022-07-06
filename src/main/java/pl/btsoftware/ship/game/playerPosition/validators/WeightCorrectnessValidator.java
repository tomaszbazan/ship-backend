package pl.btsoftware.ship.game.playerPosition.validators;

import pl.btsoftware.ship.game.board.WeightInformationChecker;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.game.playerPosition.exception.MaximumWeightExceededException;

class WeightCorrectnessValidator {
    private static final int MAX_SHIP_WEIGHT = 1300;

    static void canMoveOn(PositionOnBoard newPosition, Integer shipWeight) {
        if (shipWeight > MAX_SHIP_WEIGHT) {
            throw new MaximumWeightExceededException();
        }
        int fieldMaximumWeight = WeightInformationChecker.fieldMaximumWeight(newPosition);
        if (shipWeight > fieldMaximumWeight) {
            throw new MaximumWeightExceededException(fieldMaximumWeight);
        }
    }
}
