package pl.btsoftware.ship.game.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.Round;

import javax.persistence.*;

import static pl.btsoftware.ship.shared.Round.PREPARING;

@Entity(name = "game_state")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class GameStateEntity {
    @EmbeddedId
    private GameName name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Round round;

    boolean isPreparing() {
        return round.equals(PREPARING);
    }

    static GameStateEntity register(GameName game) {
        return new GameStateEntity(game, PREPARING);
    }

    public void nextState() {
        round = round.next();
    }
}
