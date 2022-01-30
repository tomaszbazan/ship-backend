package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.btsoftware.ship.game.events.exception.SpecialFieldTypeNotFoundException;
import pl.btsoftware.ship.game.playerInGame.PlayerNextActionDto;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@RestController
@AllArgsConstructor
@Slf4j
public class EventRestController {
    private final BottleService bottleService;

    @GetMapping(value = "/game/{gameName}/player/{playerName}/event")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PlayerNextActionDto applyEvent(@PathVariable String gameName, @PathVariable String playerName, @RequestParam SpecialFieldKind type) {
        log.info("==========================================");
        log.info("Getting event in game: " + gameName + " and player: " + playerName);
        if (type.equals(SpecialFieldKind.BOTTLE)) {
            return bottleService.accept(new GameName(gameName), new PlayerName(playerName));
        } else {
            throw new SpecialFieldTypeNotFoundException();
        }
    }
}
