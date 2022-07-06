package pl.btsoftware.ship.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.btsoftware.ship.registration.RegisterGameRestController.RegisterGameRequest;
import pl.btsoftware.ship.shared.GameName;

@Component
public class GameFixture {
    @Autowired
    private GameService gameService;

    public GameName startGame(String gameName, String gamePassword) {
        return gameService.register(new RegisterGameRequest(new GameName(gameName), gamePassword, 10));
    }
}
