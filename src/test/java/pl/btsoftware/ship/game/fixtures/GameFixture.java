package pl.btsoftware.ship.game.fixtures;

import org.apache.commons.codec.digest.DigestUtils;
import pl.btsoftware.ship.game.registration.GameEntity;
import pl.btsoftware.ship.game.registration.GameId;
import pl.btsoftware.ship.game.registration.GameName;

import java.time.LocalDate;
import java.util.UUID;

public class GameFixture {
    public static GameEntity game(GameName gameName) {
        return game(gameName, "anyGamePassword");
    }

    public static GameEntity game(GameName gameName, String gamePassword) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(new GameId(UUID.randomUUID()));
        gameEntity.setName(gameName);
        gameEntity.setPassword(DigestUtils.sha256Hex(gamePassword));
        gameEntity.setStartDate(LocalDate.of(2020, 4, 7));
        return gameEntity;
    }
}
