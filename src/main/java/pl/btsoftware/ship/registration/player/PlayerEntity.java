package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "player")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    static PlayerEntity from(RegisterPlayerRequest request, String encodedPassword) {
        return new PlayerEntity(PlayerId.newId(), PlayerName.from(request), new PlayerPassword(encodedPassword));
    }
}
