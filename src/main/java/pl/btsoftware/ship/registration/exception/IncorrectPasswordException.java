package pl.btsoftware.ship.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(GameName game) {
        super("Password to game: " + game.value() + " did you provided didn't match with creators password.");
    }

    public IncorrectPasswordException(PlayerName player) {
        super("Incorrect password for player: " + player.value());
    }
}
