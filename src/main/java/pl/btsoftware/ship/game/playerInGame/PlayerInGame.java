package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PlayerInGame {
    PlayerName playerName;
    GameName gameName;
    Country country;

    public static PlayerInGame from(PlayerInGameEntity playerInGame) {
        if(playerInGame == null) {
            return null;
        }
        return new PlayerInGame(playerInGame.getPlayerName(), playerInGame.getGameName(), playerInGame.getCountry());
    }
}
