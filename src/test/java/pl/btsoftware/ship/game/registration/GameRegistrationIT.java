package pl.btsoftware.ship.game.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class GameRegistrationIT extends IntegrationTest {
    @Autowired
    private RegisterRestController registerRestController;

    @Test
    void shouldCorrectlyJoinToNewGame() {
        // given
        PlayerName playerName = new PlayerName("PlayerOne");
        GameName gameName = registerGame();
        JoinRequest joinRequest = new JoinRequest(playerName.getName(), "anyPassword");

        // when
        PlayerJoined playerJoined = registerRestController.joinGame(gameName.getName(), joinRequest);

        // then
        assertThat(playerJoined.getPlayerName()).isEqualTo(playerName);
        assertThat(playerJoined.getGameName()).isEqualTo(gameName);
        assertThat(playerJoined.getCountry()).isEqualTo(Country.JAMAICA);
    }

    private GameName registerGame() {
        GameRequest gameRequest = GameRequest.builder().gameName("anyName").password("anyPassword").startDate(LocalDate.of(2020, 4, 7)).build();
        return registerRestController.registerGame(gameRequest);
    }
}
