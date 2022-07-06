package pl.btsoftware.ship.registration;

import pl.btsoftware.ship.registration.GameEntity.GameId;
import pl.btsoftware.ship.shared.GameName;

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
        return gameEntity;
    }
}
