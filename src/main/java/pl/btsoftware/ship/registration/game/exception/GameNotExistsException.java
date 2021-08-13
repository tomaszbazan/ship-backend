package pl.btsoftware.ship.registration.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.registration.game.GameName;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotExistsException extends RuntimeException {

    public GameNotExistsException(GameName gameName) {
        super("Game with name: " + gameName.getName() + " not found.");
    }
}
