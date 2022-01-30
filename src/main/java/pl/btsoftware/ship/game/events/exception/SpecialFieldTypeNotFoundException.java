package pl.btsoftware.ship.game.events.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SpecialFieldTypeNotFoundException extends RuntimeException {

    public SpecialFieldTypeNotFoundException() {
        super("That type is not acceptable.");
    }
}
