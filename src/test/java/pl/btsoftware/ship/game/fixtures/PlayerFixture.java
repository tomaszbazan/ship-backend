package pl.btsoftware.ship.game.fixtures;

import pl.btsoftware.ship.registration.player.PlayerEntity;
import pl.btsoftware.ship.registration.player.PlayerId;
import pl.btsoftware.ship.registration.player.PlayerName;
import pl.btsoftware.ship.registration.player.PlayerPassword;

public class PlayerFixture {
    public static PlayerEntity player(PlayerName playerName) {
        return new PlayerEntity(PlayerId.newId(), playerName, new PlayerPassword("anyPassword"));
    }
}
