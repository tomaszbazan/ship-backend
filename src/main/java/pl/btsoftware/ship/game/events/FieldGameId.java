package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PositionOnBoard;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FieldGameId implements Serializable {
    @Embedded
    private GameName game;
    private int x;
    private int y;

    public static FieldGameId from(FieldId id, GameName game) {
        return new FieldGameId(game, id.getX(), id.getY());
    }

    public static FieldGameId from(GameName game, PositionOnBoard position) {
        return new FieldGameId(game, position.getX(), position.getY());
    }
}
