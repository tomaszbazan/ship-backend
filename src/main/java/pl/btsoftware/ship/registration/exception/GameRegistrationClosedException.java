package pl.btsoftware.ship.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.GameName;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(BAD_REQUEST)
public class GameRegistrationClosedException extends RuntimeException {

    public GameRegistrationClosedException(GameName game) {
        super("Registration to game: " + game + " is closed.");
    }
}
