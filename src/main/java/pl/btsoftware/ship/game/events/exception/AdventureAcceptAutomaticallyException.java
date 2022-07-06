package pl.btsoftware.ship.game.events.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AdventureAcceptAutomaticallyException extends RuntimeException {

    public AdventureAcceptAutomaticallyException() {
        super("Adventure accepted automatically.");
    }
}
