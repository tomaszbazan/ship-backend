package pl.btsoftware.ship.creators;

import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameId;
import pl.btsoftware.ship.registration.game.GameName;

import java.time.LocalDate;

public class GameCreator {
    public static GameEntity game() {
        return game(new GameName("anyGame"), "anyGamePassword");
    }

    public static GameEntity game(GameName gameName) {
        return game(gameName, "anyGamePassword");
    }

    public static GameEntity game(GameName gameName, String gamePassword) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(new GameId());
        gameEntity.setName(gameName);
        gameEntity.setPassword(PasswordCreator.gamePassword(gamePassword));
        gameEntity.setStartDate(LocalDate.of(2020, 4, 7));
        return gameEntity;
    }
}
