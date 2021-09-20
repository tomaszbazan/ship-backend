package pl.btsoftware.ship.creators;

import pl.btsoftware.ship.registration.player.PlayerEntity;
import pl.btsoftware.ship.registration.player.PlayerId;
import pl.btsoftware.ship.registration.player.PlayerName;

public class PlayerCreator {
    public static PlayerEntity player(PlayerName playerName) {
        return new PlayerEntity(PlayerId.newId(), playerName, PasswordCreator.defaultTeamPassword());
    }
}
