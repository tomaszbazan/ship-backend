package pl.btsoftware.ship.registration;

import pl.btsoftware.ship.registration.PlayerEntity.PlayerId;
import pl.btsoftware.ship.shared.PlayerName;

public class PlayerCreator {
    public static PlayerEntity player(PlayerName playerName, String password) {
        return new PlayerEntity(PlayerId.newId(), playerName, PasswordCreator.playerPassword(password));
    }

    public static PlayerEntity player(PlayerName playerName) {
        return new PlayerEntity(PlayerId.newId(), playerName, PasswordCreator.correctPassword());
    }
}
