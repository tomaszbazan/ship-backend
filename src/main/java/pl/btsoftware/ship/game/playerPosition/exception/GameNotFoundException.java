package pl.btsoftware.ship.game.playerPosition.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.GameName;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(GameName game) {
        super("Game with name: " + game.value() + " not found.");
    }
}
