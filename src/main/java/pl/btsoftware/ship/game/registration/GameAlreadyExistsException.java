package pl.btsoftware.ship.game.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameAlreadyExistsException extends RuntimeException {

    public GameAlreadyExistsException(GameName gameName) {
        super("Game with name: " + gameName.getName() + " already exists.");
    }
}
