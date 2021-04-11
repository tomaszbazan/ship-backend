package pl.btsoftware.ship.game.registration;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.UUID;

public class GameFixture {
    public static GameEntity game(GameName gameName) {
        return game(gameName, "anyPassword");
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
