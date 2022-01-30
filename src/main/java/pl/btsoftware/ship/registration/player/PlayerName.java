package pl.btsoftware.ship.registration.player;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class PlayerName implements Serializable {
    @NonNull
    private String name;

    static PlayerName from(RegisterPlayerRequest registerPlayerRequest) {
        return new PlayerName(registerPlayerRequest.playerName());
    }
}
