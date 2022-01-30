package pl.btsoftware.ship.game.playerInGame;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.goods.Goods;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PlayerInGame {
    @JsonProperty("player_name")
    PlayerName playerName;
    @JsonProperty("game_name")
    GameName gameName;
    Country country;
    List<Goods> goods;
    @JsonProperty("first_login")
    boolean firstLogin;

    public static PlayerInGame from(PlayerInGameEntity playerInGame, boolean first) {
        if (playerInGame == null) {
            return null;
        }
        return new PlayerInGame(playerInGame.getPlayerName(), playerInGame.getGameName(), playerInGame.getCountry(), playerInGame.getGoods().stream().map(Goods::from).collect(Collectors.toList()), first);
    }
}
