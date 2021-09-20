package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    static PlayerEntity from(RegisterPlayerRequest request, PasswordEncoder passwordEncoder) {
        return new PlayerEntity(PlayerId.newId(), PlayerName.from(request), PlayerPassword.from(request, passwordEncoder));
    }
}
