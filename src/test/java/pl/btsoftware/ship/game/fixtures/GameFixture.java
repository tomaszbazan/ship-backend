package pl.btsoftware.ship.game.fixtures;

import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameId;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.GamePassword;

import java.time.LocalDate;

public class GameFixture {
    public static GameEntity game(GameName gameName) {
        return game(gameName, "anyGamePassword");
    }

    public static GameEntity game(GameName gameName, String gamePassword) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(new GameId());
        gameEntity.setName(gameName);
        gameEntity.setPassword(new GamePassword(gamePassword));
        gameEntity.setStartDate(LocalDate.of(2020, 4, 7));
        return gameEntity;
    }
}
