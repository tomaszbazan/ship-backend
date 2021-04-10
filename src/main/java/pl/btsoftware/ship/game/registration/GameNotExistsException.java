package pl.btsoftware.ship.game.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotExistsException extends RuntimeException {

    public GameNotExistsException(GameName gameName) {
        super("Game with name: " + gameName.getName() + " not found.");
    }
}
