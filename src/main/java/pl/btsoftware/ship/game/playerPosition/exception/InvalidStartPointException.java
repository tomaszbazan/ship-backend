package pl.btsoftware.ship.game.playerPosition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStartPointException extends RuntimeException {

    public InvalidStartPointException() {
        super("Before start game put your ship on first line.");
    }
}
