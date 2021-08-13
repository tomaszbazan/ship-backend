package pl.btsoftware.ship.registration.player;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class PlayerId implements Serializable {
    @NonNull
    private UUID id;

    public PlayerId() {
        this.id = UUID.randomUUID();
    }

    public static PlayerId newId() {
        return new PlayerId();
    }
}
