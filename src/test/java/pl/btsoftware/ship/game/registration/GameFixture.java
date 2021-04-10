package pl.btsoftware.ship.game.registration;

import java.time.LocalDate;
import java.util.UUID;

public class GameFixture {
    public static GameEntity game(GameName gameName) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(new GameId(UUID.randomUUID()));
        gameEntity.setName(gameName);
        gameEntity.setPassword("anyPassword");
        gameEntity.setStartDate(LocalDate.of(2020, 4, 7));
        return gameEntity;
    }
}
