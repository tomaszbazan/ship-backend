package pl.btsoftware.ship.game.state;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.playerPosition.exception.GameNotFoundException;
import pl.btsoftware.ship.game.state.exception.CantStartGameNotInPreparingStateException;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.Round;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameStateServiceTest {

    @InjectMocks
    private GameStateService gameStateService;

    @Mock
    private GameStateRepository gameStateRepository;

    @Test
    void shouldThrowGameNotFoundWhenTryingStartGame() {
        // given
        GameName game = new GameName("any");
        when(gameStateRepository.findById(game)).thenReturn(empty());

        // when & then
        assertThrows(GameNotFoundException.class, () -> gameStateService.startGame(game));
    }

    @Test
    void shouldThrowCantStartGameNotInPreparingStateWhenTryingStartGame() {
        // given
        GameName game = new GameName("any");
        when(gameStateRepository.findById(game)).thenReturn(of(new GameStateEntity(game, Round.ROUND_1_MOVE)));

        // when & then
        assertThrows(CantStartGameNotInPreparingStateException.class, () -> gameStateService.startGame(game));
    }

    @Test
    void shouldSetRound1MoveWhenTryingStartCorrectGame() {
        // given
        GameName game = new GameName("any");
        when(gameStateRepository.findById(game)).thenReturn(of(new GameStateEntity(game, Round.PREPARING)));

        // when
        gameStateService.startGame(game);

        // then
        verify(gameStateRepository).save(argThat((GameStateEntity state) -> state.getRound().equals(Round.ROUND_1_MOVE)));
    }
}
