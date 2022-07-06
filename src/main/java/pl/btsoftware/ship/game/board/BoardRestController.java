package pl.btsoftware.ship.game.board;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.btsoftware.ship.shared.GameName;

@RestController
@AllArgsConstructor
@Slf4j
class BoardRestController {
    private final BoardInformationService boardInformationService;

    @GetMapping(value = "/game/{game}/board")
    Board retrieveBoard(@PathVariable GameName game) {
        log.info("==========================================");
        log.info("Looking for situation on board for game: " + game);
        return boardInformationService.boardCreator(game);
    }
}
