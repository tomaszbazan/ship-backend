package pl.btsoftware.ship.registration.game;

import lombok.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class GameId implements Serializable {
    @NonNull
    private UUID id;

    public GameId() {
        this.id = UUID.randomUUID();
    }

    static GameId newId() {
        return new GameId();
    }
}
