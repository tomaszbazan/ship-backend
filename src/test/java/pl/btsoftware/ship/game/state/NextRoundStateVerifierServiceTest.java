package pl.btsoftware.ship.game.state;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.playerPosition.ActionType;
import pl.btsoftware.ship.game.playerPosition.PlayerMakeAction;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionService;
import pl.btsoftware.ship.shared.GameName;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NextRoundStateVerifierServiceTest {
    @InjectMocks
    private NextRoundStateVerifierService nextRoundStateVerifierService;

    @Mock
    private GameStateService gameStateService;

    @Mock
    private PlayerPositionService playerPositionService;

    @Test
    void shouldDoNothingWhenNotAllPlayersMakeMove() {
        // given
        GameName game = new GameName("any");
        when(playerPositionService.allPlayersMakeMoveInCurrentRound(game)).thenReturn(false);

        // when
        nextRoundStateVerifierService.playerMoved(new PlayerMakeAction(game, ActionType.MOVE));

        // then
        verifyNoMoreInteractions(gameStateService);
    }

    @Test
    void shouldSetNextRoundWhenAllPlayersMakeMove() {
        // given
        GameName game = new GameName("any");
        when(playerPositionService.allPlayersMakeMoveInCurrentRound(game)).thenReturn(true);

        // when
        nextRoundStateVerifierService.playerMoved(new PlayerMakeAction(game, ActionType.MOVE));

        // then
        verify(gameStateService).nextRound(any());
        verifyNoMoreInteractions(gameStateService);
    }

    @ParameterizedTest
    @EnumSource(value = ActionType.class, names = {"TREASURE", "ADVENTURE", "BOTTLE"})
    void shouldNotCountEventsAsMove(ActionType type) {
        // given
        GameName game = new GameName("any");

        // when
        nextRoundStateVerifierService.playerMoved(new PlayerMakeAction(game, type));

        // then
        verifyNoMoreInteractions(gameStateService);
    }
}
