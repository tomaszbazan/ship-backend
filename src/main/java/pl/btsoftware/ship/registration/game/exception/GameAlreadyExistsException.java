package pl.btsoftware.ship.registration.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.registration.game.GameName;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameAlreadyExistsException extends RuntimeException {

    public GameAlreadyExistsException(GameName gameName) {
        super("Game with name: " + gameName.getName() + " already exists.");
    }
}
