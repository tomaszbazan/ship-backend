package pl.btsoftware.ship.game.playerInGame.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.btsoftware.ship.game.board.BoardInformationService;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.playerInGame.exception.MaximumWeightExceededException;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class WeightCorrectnessValidatorTest {
    @InjectMocks
    private WeightCorrectnessValidator weightCorrectnessValidator;

    @Mock
    private BoardInformationService boardInformationService;

    @Test
    void shouldDoNotThrowExceptionWhenShipWeightIsLessOrEqualThanMaxWeightOfField() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(1, 1);
        Integer shipWeight = 300;
        when(boardInformationService.fieldMaximumWeight(positionOnBoard)).thenReturn(300);

        // when
        assertDoesNotThrow(() -> weightCorrectnessValidator.canMoveOn(positionOnBoard, shipWeight));
    }

    @Test
    void shouldThrowExceptionWhenShipWeightIsMoreThanMax1300() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(1, 1);
        Integer shipWeight = 1301;

        // when
        assertThrows(MaximumWeightExceededException.class, () -> weightCorrectnessValidator.canMoveOn(positionOnBoard, shipWeight));
    }

    @Test
    void shouldThrowExceptionWhenShipWeightIsMoreThanMaxWeightOfField() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(1, 1);
        Integer shipWeight = 301;
        when(boardInformationService.fieldMaximumWeight(positionOnBoard)).thenReturn(300);

        // when
        assertThrows(MaximumWeightExceededException.class, () -> weightCorrectnessValidator.canMoveOn(positionOnBoard, shipWeight));
    }
}
