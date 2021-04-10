package pl.btsoftware.ship.game.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaximumPlayersInGameException extends RuntimeException {

    public MaximumPlayersInGameException() {
        super("Maximum number of players in game is 5. Limit is reached.");
    }
}
