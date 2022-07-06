package pl.btsoftware.ship.game.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pl.btsoftware.ship.registration.GameCreated;
import pl.btsoftware.ship.shared.GameName;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "game_configuration")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class GameConfigurationEntity {
    @EmbeddedId
    private GameName name;

    @Column(nullable = false)
    private Integer roundTime;

    static GameConfigurationEntity from(GameCreated event) {
        return new GameConfigurationEntity(event.game(), event.roundTime());
    }
}
