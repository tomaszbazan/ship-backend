package pl.btsoftware.ship.game.logic;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.btsoftware.ship.game.registration.GameName;

@RestController
@AllArgsConstructor
public class BoardRestController {

    private final BoardInformationService boardInformationService;

    @GetMapping(value = "/game")
    public Board retrieveBoard() {
        return BoardCreator.create();
    }

    @GetMapping(value = "/game/{gameName}")
    public ActualBoardSituation situationOnBoard(@PathVariable String gameName) {
        return boardInformationService.actualSituation(new GameName(gameName));
    }
}
