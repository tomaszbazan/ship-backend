package pl.btsoftware.ship.game.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PositionOnBoard implements Serializable {
    public static final int MIN_X = 1;
    public static final int MAX_X = 8;
    public static final int MIN_Y = 1;
    public static final int MAX_Y = 9;

    private int x;
    private int y;

    public PositionOnBoard(int x, int y) {
        if (isNotBetween(x, MIN_X, MAX_X) || isNotBetween(y, MIN_Y, MAX_Y)) {
            throw new PlayerSituation.IncorrectPositionException();
        }
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        if (isNotBetween(x, MIN_X, MAX_X)) {
            throw new PlayerSituation.IncorrectPositionException();
        }
        this.x = x;
    }

    public void setY(int y) {
        if (isNotBetween(y, MIN_Y, MAX_Y)) {
            throw new PlayerSituation.IncorrectPositionException();
        }
        this.y = y;
    }

    private boolean isNotBetween(int number, int minValue, int maxValue) {
        return number < minValue || number > maxValue;
    }

    public boolean startLine() {
        return getY() == 1;
    }
}
