package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.board.PositionOnBoard;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FieldId implements Serializable {
    private int x;
    private int y;

    static FieldId from(PositionOnBoard positionOnBoard) {
        return new FieldId(positionOnBoard.getX(), positionOnBoard.getY());
    }
}
