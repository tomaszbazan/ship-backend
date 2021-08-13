package pl.btsoftware.ship.registration.game;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class GameName implements Serializable {
    @NonNull
    private String name;

    public static GameName from(RegisterGameRequest registerGameRequest) {
        return new GameName(registerGameRequest.getGameName());
    }
}
