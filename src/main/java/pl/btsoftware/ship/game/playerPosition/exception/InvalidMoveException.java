package pl.btsoftware.ship.game.playerPosition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException() {
        super("In one tour you can move your ship only one field at any direction.");
    }
}
