package pl.btsoftware.ship.game.events.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BottleEventNotFoundException extends RuntimeException {

    public BottleEventNotFoundException() {
        super("Event not found on player position.");
    }
}
