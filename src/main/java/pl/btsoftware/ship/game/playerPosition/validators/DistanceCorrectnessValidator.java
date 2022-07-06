package pl.btsoftware.ship.game.playerPosition.validators;

import lombok.extern.slf4j.Slf4j;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.game.playerPosition.exception.InvalidMoveException;
import pl.btsoftware.ship.game.playerPosition.exception.InvalidStartPointException;

@Slf4j
class DistanceCorrectnessValidator {
    static void canMoveOn(PositionOnBoard oldPosition, PositionOnBoard newPosition) {
        if (firstMove(oldPosition) && !newPosition.startLine()) {
            log.warn("Before start game put your ship on first line.");
            throw new InvalidStartPointException();
        }
        if (!firstMove(oldPosition) && (incorrectDistance(oldPosition, newPosition) || oldPosition.equals(newPosition))) {
            log.warn("In one tour you can move your ship only one field at any direction.");
            throw new InvalidMoveException();
        }
    }

    private static boolean incorrectDistance(PositionOnBoard oldPosition, PositionOnBoard newPosition) {
        return calculateDistance(oldPosition.getX(), newPosition.getX()) > 1 || calculateDistance(oldPosition.getY(), newPosition.getY()) > 1;
    }

    private static int calculateDistance(int startPosition, int newPosition) {
        return Math.abs(startPosition - newPosition);
    }

    private static boolean firstMove(PositionOnBoard oldPosition) {
        return oldPosition == null;
    }
}
