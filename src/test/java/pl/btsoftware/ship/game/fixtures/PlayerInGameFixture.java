package pl.btsoftware.ship.game.fixtures;

import pl.btsoftware.ship.game.board.ActualBoardSituation;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameEntity;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameId;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PlayerInGameFixture {
    public static List<ActualBoardSituation.PositionInGame> playersInGame() {
        List<ActualBoardSituation.PositionInGame> playersPosition = new ArrayList<>();

        Stream.of(Country.values())
                .forEach(country -> playersPosition.add(new ActualBoardSituation.PositionInGame() {
                    @Override
                    public String getPlayerName() {
                        return country.toString();
                    }

                    @Override
                    public String getCountry() {
                        return country.toString();
                    }

                    @Override
                    public Integer getCoordinateX() {
                        return 1;
                    }

                    @Override
                    public Integer getCoordinateY() {
                        return 1;
                    }
                }));

        return playersPosition;
    }

    public static PlayerInGameEntity createPlayer(GameEntity gameEntity, PlayerName playerName, Country country, PositionOnBoard positionOnBoard) {
        PlayerInGameEntity playerInGameEntity = new PlayerInGameEntity();

        playerInGameEntity.setId(new PlayerInGameId());
        playerInGameEntity.setPlayer(PlayerFixture.player(playerName));
        playerInGameEntity.setGame(gameEntity);
        playerInGameEntity.setCountry(country);
        playerInGameEntity.setPositionOnBoard(positionOnBoard);

        return playerInGameEntity;
    }
}
