package pl.btsoftware.ship.game.state;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.btsoftware.ship.shared.GameName;

@RestController
@AllArgsConstructor
@Slf4j
class StateRestController {
    private final GameStateService gameStateService;

    @PostMapping(value = "/game/{game}/start")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void playerMove(@PathVariable GameName game) {
        log.info("==========================================");
        log.info("Starting game: " + game);
        gameStateService.startGame(game);
    }
}
