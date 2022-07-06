package pl.btsoftware.ship.game.playerPosition.validators;

import pl.btsoftware.ship.shared.PositionOnBoard;

public class MoveCorrectnessValidator {
    public static void canMoveOn(PositionOnBoard oldPosition, PositionOnBoard newPosition, Integer shipWeight) {
        DistanceCorrectnessValidator.canMoveOn(oldPosition, newPosition);
        WeightCorrectnessValidator.canMoveOn(newPosition, shipWeight);
    }
}
