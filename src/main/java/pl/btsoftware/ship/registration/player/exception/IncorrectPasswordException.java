package pl.btsoftware.ship.registration.player.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(GameName gameName) {
        super("Password to game: " + gameName.getName() + " did you provided didn't match with creators password.");
    }

    public IncorrectPasswordException(PlayerName playerName) {
        super("Incorrect password for player: " + playerName.getName());
    }
}
