package pl.btsoftware.ship.game.fixtures;

import pl.btsoftware.ship.game.registration.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PlayerInGameFixture {
    public static List<PlayerInGameEntity> playersInGame(GameName gameName) {
        List<PlayerInGameEntity> playerInGameEntities = new ArrayList<>();

        GameEntity gameEntity = GameFixture.game(gameName);

        Stream.of(Country.values())
                .forEach(country -> playerInGameEntities.add(createPlayer(gameEntity, country)));

        return playerInGameEntities;
    }

    private static PlayerInGameEntity createPlayer(GameEntity gameEntity, Country country) {
        PlayerInGameEntity playerInGameEntity = new PlayerInGameEntity();

        playerInGameEntity.setId(new PlayerInGameId());
        playerInGameEntity.setPlayer(PlayerFixture.player(new PlayerName("anyName" + country)));
        playerInGameEntity.setGame(gameEntity);
        playerInGameEntity.setCountry(country);

        return playerInGameEntity;
    }
}
