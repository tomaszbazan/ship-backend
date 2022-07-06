package pl.btsoftware.ship.game.state.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.GameName;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class CantStartGameNotInPreparingStateException extends RuntimeException {

    public CantStartGameNotInPreparingStateException(GameName game) {
        super("Can't start game: " + game.value() + ". You can start game only in preparing state.");
    }
}
