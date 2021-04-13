package pl.btsoftware.ship.game.fixtures;

import org.apache.commons.codec.digest.DigestUtils;
import pl.btsoftware.ship.game.registration.PlayerEntity;
import pl.btsoftware.ship.game.registration.PlayerId;
import pl.btsoftware.ship.game.registration.PlayerName;

public class PlayerFixture {
    public static PlayerEntity player(PlayerName playerName) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(new PlayerId());
        playerEntity.setName(playerName);
        playerEntity.setPassword(DigestUtils.sha256Hex("anyPassword"));

        return playerEntity;
    }
}
