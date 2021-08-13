package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.btsoftware.ship.game.playerInGame.PlayerInGame;
import pl.btsoftware.ship.registration.game.GameName;

@RestController
@AllArgsConstructor
@Slf4j
public class RegisterPlayerRestController {

    private final PlayerService playerService;

    @PostMapping(value = "/game/{gameName}/player")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerInGame joinGame(@PathVariable String gameName, @RequestBody RegisterPlayerRequest registerPlayerRequest) {
        return playerService.joinPlayer(new GameName(gameName), registerPlayerRequest);
    }
}
