package pl.btsoftware.ship.registration.game;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "game")
@Data
@NoArgsConstructor
public class GameEntity {
    @EmbeddedId
    @Column(nullable = false)
    private GameId id;

    @Embedded
    @Column(nullable = false)
    private GameName name;

    @Embedded
    @Column(nullable = false)
    private GamePassword password;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    public static GameEntity from(RegisterGameRequest registerGameRequest, PasswordEncoder passwordEncoder) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(GameId.newId());
        gameEntity.setName(GameName.from(registerGameRequest));
        gameEntity.setPassword(GamePassword.from(registerGameRequest, passwordEncoder));
        gameEntity.setStartDate(registerGameRequest.getStartDate());

        return gameEntity;
    }
}
