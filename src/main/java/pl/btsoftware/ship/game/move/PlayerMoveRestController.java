package pl.btsoftware.ship.game.move;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.playerInGame.PlayerMoverService;
import pl.btsoftware.ship.game.playerInGame.PlayerNextActionDto;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@RestController
@AllArgsConstructor
@Slf4j
public class PlayerMoveRestController {
    private final PlayerMoverService playerMoverService;

    @PostMapping(value = "/game/{gameName}/player/{playerName}/move")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PlayerNextActionDto playerMove(@PathVariable String gameName, @PathVariable String playerName, @RequestBody PositionOnBoard positionOnBoard) {
        log.info("Making move in game: " + gameName + " and player: " + playerName + " on " + positionOnBoard.toString());
        return playerMoverService.movePlayer(new GameName(gameName), new PlayerName(playerName), positionOnBoard);
    }
}
