package pl.btsoftware.ship.registration.player;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class PlayerPassword {
    @NonNull
    private String password;

    static PlayerPassword from(RegisterPlayerRequest registerPlayerRequest) {
        return new PlayerPassword(registerPlayerRequest.getTeamPassword());
    }

    public PlayerPassword(@NonNull String password) {
        this.password = DigestUtils.sha256Hex(password);
    }
}
