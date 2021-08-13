package pl.btsoftware.ship.game.playerInGame;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class PlayerInGameId implements Serializable {
    @NonNull
    private UUID id;

    public PlayerInGameId() {
        this.id = UUID.randomUUID();
    }

    static PlayerInGameId newId() {
        return new PlayerInGameId();
    }
}
