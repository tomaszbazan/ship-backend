package pl.btsoftware.ship.game.playerPosition;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.btsoftware.ship.game.events.EventSnapshot;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
class PlayerMoveRestController {
    private final PlayerMoveService playerMoveService;

    @PostMapping(value = "/game/{game}/player/{player}/move")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Optional<EventSnapshot.EventDescription> playerMove(@PathVariable GameName game, @PathVariable PlayerName player, @RequestBody PositionOnBoard positionOnBoard) {
        log.info("==========================================");
        log.info("Making move in game: " + game + " and player: " + player + " on " + positionOnBoard);
        return playerMoveService.movePlayer(game, player, positionOnBoard);
    }
}
