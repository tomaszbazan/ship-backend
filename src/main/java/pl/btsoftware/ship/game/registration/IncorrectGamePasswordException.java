package pl.btsoftware.ship.game.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectGamePasswordException extends RuntimeException {

    public IncorrectGamePasswordException(GameName gameName) {
        super("Password to game: " + gameName.getName() + " did you provided didn't match with creators password");
    }
}
