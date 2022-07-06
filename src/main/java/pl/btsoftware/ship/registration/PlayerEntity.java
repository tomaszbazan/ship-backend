package pl.btsoftware.ship.registration;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.btsoftware.ship.shared.PlayerName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "player")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Accessors(chain = true)
public class PlayerEntity {
    @EmbeddedId
    @Column(nullable = false)
    private PlayerId id;

    @Embedded
    @Column(nullable = false)
    private PlayerName name;

    @Embedded
    @Column(nullable = false)
    private PlayerPassword password;

    static PlayerEntity from(RegisterPlayerRestController.RegisterPlayerRequest request, String encodedPassword) {
        return new PlayerEntity(PlayerId.newId(), request.player(), new PlayerPassword(encodedPassword));
    }

    PlayerName playerName() {
        return getName();
    }

    @Embeddable
    static class PlayerId implements Serializable {
        @NonNull
        private UUID id;

        public PlayerId() {
            this.id = UUID.randomUUID();
        }

        public static PlayerId newId() {
            return new PlayerId();
        }
    }

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    static class PlayerPassword {
        @NonNull
        private String password;

        static PlayerPassword from(RegisterPlayerRestController.RegisterPlayerRequest registerPlayerRequest, PasswordEncoder passwordEncoder) {
            return new PlayerPassword(registerPlayerRequest.playerPassword(), passwordEncoder);
        }

        PlayerPassword(@NonNull String password, PasswordEncoder passwordEncoder) {
            this.password = passwordEncoder.encode(password);
        }

        public String value() {
            return this.password;
        }
    }
}
