package pl.btsoftware.ship.registration.game;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class RegisterGameRestController {

    private final GameService gameService;

    @PostMapping(value = "/game")
    @ResponseStatus(HttpStatus.CREATED)
    public GameName registerGame(@RequestBody RegisterGameRequest gameRegisterRequest) {
        return gameService.register(gameRegisterRequest);
    }
}
