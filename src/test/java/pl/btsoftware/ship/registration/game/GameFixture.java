package pl.btsoftware.ship.registration.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GameFixture {
    @Autowired
    private GameService gameService;

    public void createGame(String gameName, String gamePassword) {
        gameService.register(new RegisterGameRequest(gameName, gamePassword, LocalDate.now()));
    }
}
