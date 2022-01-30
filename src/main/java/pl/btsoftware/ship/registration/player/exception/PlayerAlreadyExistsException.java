package pl.btsoftware.ship.registration.player.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException(PlayerName playerName) {
        super("Player with name: " + playerName.getName() + " already exists.");
    }
}
