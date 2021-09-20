package pl.btsoftware.ship.creators;

import pl.btsoftware.ship.game.board.PlayerSituation;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.goods.GoodsEntity;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameEntity;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameId;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerInGameCreator {
    public static List<PlayerSituation> playersInGame() {
        List<PlayerSituation> playersPosition = new ArrayList<>();

        Arrays.stream(Country.values()).forEach(country -> playersPosition.add(new PlayerSituation(country.toString(), country, new PositionOnBoard(1, 1))));

        return playersPosition;
    }

    public static PlayerInGameEntity createPlayer(GameEntity gameEntity, PlayerName playerName, Country country, PositionOnBoard positionOnBoard) {
        PlayerInGameEntity playerInGameEntity = new PlayerInGameEntity();

        playerInGameEntity.setId(new PlayerInGameId());
        playerInGameEntity.setPlayer(PlayerCreator.player(playerName));
        playerInGameEntity.setGame(gameEntity);
        playerInGameEntity.setCountry(country);
        playerInGameEntity.setPositionOnBoard(positionOnBoard);

        return playerInGameEntity;
    }

    public static PlayerInGameEntity createPlayer(GameName gameName, PlayerName playerName) {
        PlayerInGameEntity playerInGameEntity = new PlayerInGameEntity();

        playerInGameEntity.setId(new PlayerInGameId());
        playerInGameEntity.setPlayer(PlayerCreator.player(playerName));
        playerInGameEntity.setGame(GameCreator.game(gameName));
        playerInGameEntity.setCountry(Country.JAMAICA);
        playerInGameEntity.setPositionOnBoard(null);
        playerInGameEntity.setGoods(GoodsCreator.jamaicaStartGoods().stream().map(GoodsEntity::from).collect(Collectors.toList()));

        return playerInGameEntity;
    }
}
