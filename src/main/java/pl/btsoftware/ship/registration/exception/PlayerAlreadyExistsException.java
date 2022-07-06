package pl.btsoftware.ship.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.PlayerName;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException(PlayerName player) {
        super("Player with name: " + player.value() + " already exists.");
    }
}
