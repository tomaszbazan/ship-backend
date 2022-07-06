package pl.btsoftware.ship.game.playerPosition.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.game.playerPosition.exception.InvalidMoveException;
import pl.btsoftware.ship.game.playerPosition.exception.InvalidStartPointException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DistanceCorrectnessValidatorTest {
    private static Stream<Arguments> correctMoves() {
        return Stream.of(
                Arguments.of(null, new PositionOnBoard(3, 1)),
                Arguments.of(null, new PositionOnBoard(5, 1)),
                Arguments.of(null, new PositionOnBoard(8, 1)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(2, 2)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(3, 2)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(4, 2)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(2, 3)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(4, 3)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(2, 4)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(3, 4)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(4, 4))
        );
    }

    @ParameterizedTest
    @MethodSource("correctMoves")
    public void shouldSaveCorrectMove(PositionOnBoard startPosition, PositionOnBoard moveOn) {
        // when & then
        assertDoesNotThrow(() -> DistanceCorrectnessValidator.canMoveOn(startPosition, moveOn));
    }

    @Test
    void shouldThrowInvalidStartPointExceptionIfStartPositionIsNullAndNewCoordinateIsNotInFirstLine() {
        // given
        PositionOnBoard oldPosition = null;
        PositionOnBoard newPosition = new PositionOnBoard(1, 3);

        // when & then
        assertThrows(InvalidStartPointException.class, () -> DistanceCorrectnessValidator.canMoveOn(oldPosition, newPosition));
    }

    @Test
    void shouldThrowInvalidMoveExceptionIfStartPositionIsNotNullAndNewCoordinateIsInMoreThanOneField() {
        // given
        PositionOnBoard oldPosition = new PositionOnBoard(1, 1);
        PositionOnBoard newPosition = new PositionOnBoard(1, 3);

        // when & then
        assertThrows(InvalidMoveException.class, () -> DistanceCorrectnessValidator.canMoveOn(oldPosition, newPosition));
    }

    @Test
    void shouldThrowInvalidMoveExceptionIfStartPositionAndNewCoordinateAreEquals() {
        // given
        PositionOnBoard oldPosition = new PositionOnBoard(3, 3);
        PositionOnBoard newPosition = new PositionOnBoard(3, 3);

        // when & then
        assertThrows(InvalidMoveException.class, () -> DistanceCorrectnessValidator.canMoveOn(oldPosition, newPosition));
    }

}
