package pl.btsoftware.ship.game.playerInGame.validators;

import org.junit.jupiter.api.Test;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.playerInGame.exception.InvalidMoveException;
import pl.btsoftware.ship.game.playerInGame.exception.InvalidStartPointException;

import static org.junit.jupiter.api.Assertions.*;

class MoveCorrectnessValidatorTest {
    @Test
    void shouldThrowInvalidStartPointExceptionIfStartPositionIsNullAndNewCoordinateIsNotInFirstLine() {
        // given
        PositionOnBoard oldPosition = null;
        PositionOnBoard newPosition = new PositionOnBoard(1, 3);

        // when & then
        assertThrows(InvalidStartPointException.class, () -> MoveCorrectnessValidator.canMoveOn(oldPosition, newPosition));
    }

    @Test
    void shouldThrowInvalidMoveExceptionIfStartPositionIsNotNullAndNewCoordinateIsInMoreThanOneField() {
        // given
        PositionOnBoard oldPosition = new PositionOnBoard(1, 1);
        PositionOnBoard newPosition = new PositionOnBoard(1, 3);

        // when & then
        assertThrows(InvalidMoveException.class, () -> MoveCorrectnessValidator.canMoveOn(oldPosition, newPosition));
    }

    @Test
    void shouldThrowInvalidMoveExceptionIfStartPositionAndNewCoordinateAreEquals() {
        // given
        PositionOnBoard oldPosition = new PositionOnBoard(3, 3);
        PositionOnBoard newPosition = new PositionOnBoard(3, 3);

        // when & then
        assertThrows(InvalidMoveException.class, () -> MoveCorrectnessValidator.canMoveOn(oldPosition, newPosition));
    }

}
