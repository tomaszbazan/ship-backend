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
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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
        List<PlayerSituation> actualBoardSituation = boardInformationService.actualSituation(gameName);

        // then
        assertThat(actualBoardSituation).hasSize(5);
    }

    @Test
    void shouldThrowGameNotFoundWhenGameNotExistsOnGeneratingBoard() {
        // given
        GameName gameName = new GameName(UUID.randomUUID().toString());
        when(playerInGameService.getActualSituationOnBoard(gameName)).thenReturn(Collections.emptyList());

        // when & then
        assertThrows(GameNotExistsException.class, () -> boardInformationService.boardCreator(gameName));
    }

    @Test
    void shouldFindCorrectWeightOnBoard() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(1,1);

        // when
        int weight = boardInformationService.fieldMaximumWeight(positionOnBoard);

        // then
        assertThat(weight).isEqualTo(770);
    }
}
