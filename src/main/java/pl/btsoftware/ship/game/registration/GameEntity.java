package pl.btsoftware.ship.game.registration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

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

    @Column(nullable = false)
    private String password;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    GameEntity(GameRequest gameRequest) {
        setId(new GameId(UUID.randomUUID()));
        setName(new GameName(gameRequest.getGameName()));
        setPassword(DigestUtils.sha256Hex(gameRequest.getPassword()));
        setStartDate(gameRequest.getStartDate());
    }
}
