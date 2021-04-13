package pl.btsoftware.ship.game.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.registration.GameName;
import pl.btsoftware.ship.game.registration.PlayerInGameRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pl.btsoftware.ship.game.fixtures.PlayerInGameFixture.playersInGame;

@ExtendWith(MockitoExtension.class)
class BoardInformationServiceTest {

    @InjectMocks
    private BoardInformationService boardInformationService;

    @Mock
    private PlayerInGameRepository playerInGameRepository;

    @Test
    void shouldTakePlayersFromDatabase() {
        // given
        GameName gameName = new GameName("anyName");
        when(playerInGameRepository.getAllByGame_Name(gameName)).thenReturn(playersInGame(gameName));

        // when
        ActualBoardSituation actualBoardSituation = boardInformationService.actualSituation(gameName);

        // then
        assertThat(actualBoardSituation.getPlayers()).hasSize(5);
    }
}
