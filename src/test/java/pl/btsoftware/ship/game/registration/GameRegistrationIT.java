package pl.btsoftware.ship.game.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;

import java.time.LocalDate;

public class GameRegistrationIT extends IntegrationTest {
    @Autowired
    private RegisterRestController registerRestController;

    @Test
    void shouldCorrectlyJoinToNewGame() {
        // given
        GameName gameName = registerGame();

        // when

        // then
    }

    private GameName registerGame() {
        GameRequest gameRequest = GameRequest.builder().gameName("anyName").password("anyPassword").startDate(LocalDate.of(2020, 4, 7)).build();
        return registerRestController.registerGame(gameRequest);
    }
}
