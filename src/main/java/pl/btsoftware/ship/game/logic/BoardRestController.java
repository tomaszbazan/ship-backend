package pl.btsoftware.ship.game.logic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {

    @GetMapping(value = "/game")
    public Board retrieveBoard() {
        return BoardCreator.create();
    }
}
