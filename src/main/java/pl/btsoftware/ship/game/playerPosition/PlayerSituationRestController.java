package pl.btsoftware.ship.game.playerPosition;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

@RestController
@AllArgsConstructor
@Slf4j
class PlayerSituationRestController {

    private final PlayerPositionService playerPositionService;

    @GetMapping(value = "/game/{game}/player/{player}")
    PlayerPositionSnapshot stateOfPlayer(@PathVariable GameName game, @PathVariable PlayerName player) {
        log.info("==========================================");
        log.info("Looking for player state for game: " + game + " and player: " + player);
        return playerPositionService.get(game, player);
    }
}
