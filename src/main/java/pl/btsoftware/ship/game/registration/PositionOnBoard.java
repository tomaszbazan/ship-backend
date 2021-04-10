package pl.btsoftware.ship.game.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
public class PositionOnBoard implements Serializable {
    private int x;
    private int y;

    public PositionOnBoard(int x, int y) {
        if (isNotBetween(x, 1, 8) || isNotBetween(y, 1, 8)) {
            throw new IncorrectPositionException();
        }
        this.x = x;
        this.y = y;
    }

    private boolean isNotBetween(int number, int minValue, int maxValue) {
        return number < minValue || number > maxValue;
    }
}
