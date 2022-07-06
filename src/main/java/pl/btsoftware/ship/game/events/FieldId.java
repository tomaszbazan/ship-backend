package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.shared.PositionOnBoard;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FieldId implements Serializable {
    private int x;
    private int y;

    public static FieldId from(PositionOnBoard positionOnBoard) {
        return new FieldId(positionOnBoard.getX(), positionOnBoard.getY());
    }
}
