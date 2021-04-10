package pl.btsoftware.ship.game.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PlayerId implements Serializable {
    @NonNull
    private UUID id;
}
