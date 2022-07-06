package pl.btsoftware.ship.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.GameName;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class GameAlreadyExistsException extends RuntimeException {

    public GameAlreadyExistsException(GameName game) {
        super("Game with name: " + game.value() + " already exists.");
    }
}
