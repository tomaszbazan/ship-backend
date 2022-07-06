package pl.btsoftware.ship.game.playerPosition.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.playerPosition.exception.CantMoveInFinishedStateException;
import pl.btsoftware.ship.game.playerPosition.exception.CantMoveInPreparingStateException;
import pl.btsoftware.ship.game.state.GameStateService;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.Round;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameValidatorTest {
    @InjectMocks
    private GameValidator gameValidator;

    @Mock
    private GameStateService gameStateService;

    @Test
    void shouldThrowExceptionWhenGameIsInPreparingState() {
        // given
        when(gameStateService.state(any())).thenReturn(Round.PREPARING);

        // when & then
        assertThrows(CantMoveInPreparingStateException.class, () -> gameValidator.canMove(new GameName("any")));
    }

    @Test
    void shouldThrowExceptionWhenGameIsInFinishedState() {
        // given
        when(gameStateService.state(any())).thenReturn(Round.FINISHED);

        // when & then
        assertThrows(CantMoveInFinishedStateException.class, () -> gameValidator.canMove(new GameName("any")));
    }

}
