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
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pl.btsoftware.ship.game.fixtures.PlayerInGameFixture.playersInGame;

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
        when(playerInGameService.getLastMoveForEachPlayerInGame(gameName)).thenReturn(playersInGame());

        // when
        ActualBoardSituation actualBoardSituation = boardInformationService.actualSituation(gameName);

        // then
        Assertions.assertThat(actualBoardSituation.getPlayers()).hasSize(5);
    }

    @Test
    void shouldThrowGameNotFoundWhenGameNotExistsOnTryingTakeInformationAboutAllPlayersInGame() {
        // given
        GameName gameName = new GameName(UUID.randomUUID().toString());
        when(playerInGameService.getLastMoveForEachPlayerInGame(gameName)).thenReturn(Collections.emptyList());

        // when & then
        assertThrows(GameNotExistsException.class, () -> boardInformationService.actualSituation(gameName));
    }

    @Test
    void shouldTakeInformationAboutSinglePlayerInGame() {
        // given
        GameName gameName = new GameName(UUID.randomUUID().toString());
        PlayerName playerName = new PlayerName("JAMAICA");
        when(playerInGameService.getLastMoveForEachPlayerInGame(gameName)).thenReturn(playersInGame());

        // when
        ActualBoardSituation actualBoardSituation = boardInformationService.actualSituation(gameName, playerName);

        // then
        Assertions.assertThat(actualBoardSituation.getPlayers()).hasSize(1);
        assertThat(actualBoardSituation.getPlayers().get(0).getPlayerName()).isEqualTo(playerName.getName());
    }

    @Test
    void shouldReturnEmptyArrayWhenPlayerNotExistsInSpecifiedGame() {
        // given
        GameName gameName = new GameName("anyName");
        PlayerName playerName = new PlayerName("anyPlayerName");
        when(playerInGameService.getLastMoveForEachPlayerInGame(gameName)).thenReturn(playersInGame());

        // when
        ActualBoardSituation actualBoardSituation = boardInformationService.actualSituation(gameName, playerName);

        // then
        Assertions.assertThat(actualBoardSituation.getPlayers()).hasSize(0);
    }
}
