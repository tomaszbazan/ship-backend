package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@RestController
@AllArgsConstructor
@Slf4j
public class PlayerInGameRestController {

    private final PlayerInGameService playerInGameService;

    @GetMapping(value = "/game/{gameName}/player/{playerName}")
    public PlayerInGame stateOfPlayer(@PathVariable String gameName, @PathVariable String playerName) {
        log.info("==========================================");
        log.info("Looking for player state for game: " + gameName + " and player: " + playerName);
        PlayerInGame playerInGame = playerInGameService.findPlayerInGame(new GameName(gameName), new PlayerName(playerName));
        playerInGame.goods.forEach(s -> log.info(s.type() + ": " + s.amount()));
        return playerInGame;
    }
}
