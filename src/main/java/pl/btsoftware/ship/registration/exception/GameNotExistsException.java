package pl.btsoftware.ship.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.GameName;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class GameNotExistsException extends RuntimeException {

    public GameNotExistsException(GameName game) {
        super("Game with name: " + game.value() + " not exists.");
    }
}
