package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

@RestController
@AllArgsConstructor
@Slf4j
class EventRestController {
    private final EventSelector eventSelector;

    @PostMapping(value = "/game/{game}/player/{player}/event", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    EventsService.EventDescription applyEvent(@PathVariable String game, @PathVariable String player, @RequestBody EventResponse response) {
        log.info("==========================================");
        log.info("Getting event in game: " + game + " and player: " + player);
        if (response.accepted) {
            return eventSelector.accept(response.type, new GameName(game), new PlayerName(player));
        } else {
            eventSelector.decline(response.type, new GameName(game), new PlayerName(player));
            return null;
        }
    }
    record EventResponse(SpecialFieldKind type, boolean accepted) {
    }
}
