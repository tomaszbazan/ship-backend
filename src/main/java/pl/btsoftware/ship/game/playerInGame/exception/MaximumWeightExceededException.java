package pl.btsoftware.ship.game.playerInGame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaximumWeightExceededException extends RuntimeException {

    public MaximumWeightExceededException() {
        super("Total weight of your ship is more that acceptable.");
    }

    public MaximumWeightExceededException(int maxForField) {
        super(format("Total acceptable weight of your ship on that field is %d.", maxForField));
    }
}
