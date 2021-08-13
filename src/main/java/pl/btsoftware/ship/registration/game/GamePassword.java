package pl.btsoftware.ship.registration.game;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class GamePassword {
    @NonNull
    private String password;

    static GamePassword from(RegisterGameRequest registerGameRequest) {
        return new GamePassword(registerGameRequest.getPassword());
    }

    public GamePassword(@NonNull String password) {
        this.password = DigestUtils.sha256Hex(password);
    }
}
