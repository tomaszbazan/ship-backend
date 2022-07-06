package pl.btsoftware.ship.game.playerPosition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundInGameException extends RuntimeException {

    public PlayerNotFoundInGameException(GameName gameName, PlayerName playerName) {
        super("Player with name: " + playerName.value() + " not found in game: " + gameName.value());
    }
}
