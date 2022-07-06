package pl.btsoftware.ship.game.playerPosition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CantMoveInFinishedStateException extends RuntimeException {
    public CantMoveInFinishedStateException() {
        super("Can't move in finished state.");
    }
}
