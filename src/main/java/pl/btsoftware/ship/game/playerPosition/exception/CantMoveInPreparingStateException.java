package pl.btsoftware.ship.game.playerPosition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CantMoveInPreparingStateException extends RuntimeException {
    public CantMoveInPreparingStateException() {
        super("Can't move in preparing state. Please wait until all players will join.");
    }
}
