package pl.btsoftware.ship.game.board;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.btsoftware.ship.registration.game.GameName;

@RestController
@AllArgsConstructor
@Slf4j
public class BoardRestController {

    private final BoardInformationService boardInformationService;

    @GetMapping(value = "/board")
    public Board retrieveBoard() {
        log.info("Retrieve board");
        return BoardCreator.create();
    }

    @GetMapping(value = "/game/{gameName}/board")
    public ActualBoardSituation situationOnBoard(@PathVariable String gameName) {
        log.info("Looking for situation on board for game: " + gameName);
        return boardInformationService.actualSituation(new GameName(gameName));
    }
}
