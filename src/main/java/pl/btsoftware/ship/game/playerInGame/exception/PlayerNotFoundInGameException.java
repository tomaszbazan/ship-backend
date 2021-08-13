package pl.btsoftware.ship.game.playerInGame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundInGameException extends RuntimeException {

    public PlayerNotFoundInGameException(GameName gameName, PlayerName playerName) {
        super("Player with name: " + playerName.getName() + " not found in game: " + gameName.getName());
    }
}
