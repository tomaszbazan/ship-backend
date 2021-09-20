package pl.btsoftware.ship.registration.game;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class GamePassword {
    @NonNull
    private String password;

    static GamePassword from(RegisterGameRequest registerGameRequest, PasswordEncoder passwordEncoder) {
        return new GamePassword(registerGameRequest.getPassword(), passwordEncoder);
    }

    public GamePassword(@NonNull String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public String value() {
        return password;
    }
}
