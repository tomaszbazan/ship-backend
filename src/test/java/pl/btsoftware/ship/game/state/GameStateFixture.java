package pl.btsoftware.ship.game.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.btsoftware.ship.shared.GameName;

@Component
public class GameStateFixture {
    @Autowired
    GameStateService gameStateService;

    public void startGame(GameName game) {
        gameStateService.startGame(game);
    }

}
