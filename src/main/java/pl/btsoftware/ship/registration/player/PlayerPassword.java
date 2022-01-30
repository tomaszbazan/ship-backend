package pl.btsoftware.ship.registration.player;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPassword {
    @NonNull
    private String password;

    static PlayerPassword from(RegisterPlayerRequest registerPlayerRequest, PasswordEncoder passwordEncoder) {
        return new PlayerPassword(registerPlayerRequest.playerPassword(), passwordEncoder);
    }

    public PlayerPassword(@NonNull String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public String value() {
        return this.password;
    }
}
