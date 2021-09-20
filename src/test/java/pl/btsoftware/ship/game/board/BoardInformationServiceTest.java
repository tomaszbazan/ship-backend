package pl.btsoftware.ship.game.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pl.btsoftware.ship.creators.PlayerInGameCreator.playersInGame;

@ExtendWith(MockitoExtension.class)
class BoardInformationServiceTest {

    @InjectMocks
    private BoardInformationService boardInformationService;

    @Mock
    private PlayerInGameService playerInGameService;

    @Test
    void shouldReturnInformationAboutAllPlayersInGame() {
        // given
        GameName gameName = new GameName(UUID.randomUUID().toString());
        when(playerInGameService.getActualSituationOnBoard(gameName)).thenReturn(playersInGame());

        // when
        ActualBoardSituation actualBoardSituation = boardInformationService.actualSituation(gameName);

        // then
        Assertions.assertThat(actualBoardSituation.getPlayers()).hasSize(5);
    }

    @Test
    void shouldThrowGameNotFoundWhenGameNotExistsOnTryingTakeInformationAboutAllPlayersInGame() {
        // given
        GameName gameName = new GameName(UUID.randomUUID().toString());
        when(playerInGameService.getActualSituationOnBoard(gameName)).thenReturn(Collections.emptyList());

        // when & then
        assertThrows(GameNotExistsException.class, () -> boardInformationService.actualSituation(gameName));
    }
}
