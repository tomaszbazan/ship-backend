package pl.btsoftware.ship.game.playerPosition.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.PositionOnBoard;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectPositionException extends RuntimeException {
    public IncorrectPositionException() {
        super("Coordinate must meet conditions: " + PositionOnBoard.MIN_X + " <= x <= " + PositionOnBoard.MAX_X + " , " + PositionOnBoard.MIN_Y + " <= y <= " + PositionOnBoard.MAX_Y);
    }
}
