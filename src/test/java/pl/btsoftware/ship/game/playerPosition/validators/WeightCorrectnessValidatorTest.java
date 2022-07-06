package pl.btsoftware.ship.game.playerPosition.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.board.BoardInformationService;
import pl.btsoftware.ship.game.board.WeightInformationChecker;
import pl.btsoftware.ship.game.playerPosition.exception.MaximumWeightExceededException;
import pl.btsoftware.ship.shared.PositionOnBoard;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class WeightCorrectnessValidatorTest {

    @Test
    void shouldDoNotThrowExceptionWhenShipWeightIsLessOrEqualThanMaxWeightOfField() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(3, 1);
        Integer shipWeight = 750;

        // when
        assertDoesNotThrow(() -> WeightCorrectnessValidator.canMoveOn(positionOnBoard, shipWeight));
    }

    @Test
    void shouldThrowExceptionWhenShipWeightIsMoreThanMax1300() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(1, 1);
        Integer shipWeight = 1301;

        // when
        assertThrows(MaximumWeightExceededException.class, () -> WeightCorrectnessValidator.canMoveOn(positionOnBoard, shipWeight));
    }

    @Test
    void shouldThrowExceptionWhenShipWeightIsMoreThanMaxWeightOfField() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(3, 1);
        Integer shipWeight = 751;

        // when
        assertThrows(MaximumWeightExceededException.class, () -> WeightCorrectnessValidator.canMoveOn(positionOnBoard, shipWeight));
    }
}
