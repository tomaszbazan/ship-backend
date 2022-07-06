package pl.btsoftware.ship.registration;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.btsoftware.ship.shared.GameName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "game")
@Data
@Accessors(chain = true)
@NoArgsConstructor
class GameEntity {
    @EmbeddedId
    @Column(nullable = false)
    private GameId id;

    @Embedded
    @Column(nullable = false)
    private GameName name;

    @Embedded
    @Column(nullable = false)
    private GamePassword password;

    static GameEntity from(RegisterGameRestController.RegisterGameRequest registerGameRequest, PasswordEncoder passwordEncoder) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(GameId.newId());
        gameEntity.setName(registerGameRequest.game());
        gameEntity.setPassword(GamePassword.from(registerGameRequest, passwordEncoder));

        return gameEntity;
    }

    GameName gameName() {
        return getName();
    }

    String passwordValue() {
        return password.value();
    }

    @Embeddable
    @NoArgsConstructor
    static class GamePassword {
        @NonNull
        private String password;

        static GamePassword from(RegisterGameRestController.RegisterGameRequest registerGameRequest, PasswordEncoder passwordEncoder) {
            return new GamePassword(registerGameRequest.password(), passwordEncoder);
        }

        GamePassword(@NonNull String password, PasswordEncoder passwordEncoder) {
            this.password = passwordEncoder.encode(password);
        }

        String value() {
            return password;
        }
    }

    @Embeddable
    static class GameId implements Serializable {
        @NonNull
        private UUID id;

        public GameId() {
            this.id = UUID.randomUUID();
        }

        static GameId newId() {
            return new GameId();
        }
    }
}
