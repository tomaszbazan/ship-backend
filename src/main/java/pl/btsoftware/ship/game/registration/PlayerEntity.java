package pl.btsoftware.ship.game.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "player")
@Data
@NoArgsConstructor
public class PlayerEntity {
    @EmbeddedId
    @Column(nullable = false)
    private PlayerId id;

    @Embedded
    @Column(nullable = false)
    private PlayerName name;

    @Column(nullable = false)
    private String password;

    PlayerEntity(JoinRequest joinRequest) {
        setId(new PlayerId(UUID.randomUUID()));
        setName(new PlayerName(joinRequest.getPlayerName()));
        setPassword(joinRequest.getPassword());
    }
}
