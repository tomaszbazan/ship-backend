package pl.btsoftware.ship.game.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PlayerJoined {
    PlayerName playerName;
    GameName gameName;
    Country country;

    PlayerJoined(PlayerInGameEntity playerInGameEntity) {
        this.playerName = playerInGameEntity.getPlayer().getName();
        this.gameName = playerInGameEntity.getGame().getName();
        this.country = playerInGameEntity.getCountry();
    }
}
