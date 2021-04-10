package pl.btsoftware.ship.game.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class RegisterRestController {

    private final GameRegistrationService gameRegistrationService;
    private final GameJoinerService gameJoinerService;

    @PostMapping(value = "/game")
    @ResponseStatus(code = HttpStatus.CREATED)
    public GameName registerGame(@RequestBody GameRequest gameRegisterRequest) {
        return gameRegistrationService.register(gameRegisterRequest);
    }

    @PostMapping(value = "/game/{gameName}/player")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PlayerJoined joinGame(@PathVariable String gameName, @RequestBody JoinRequest joinRequest) {
        return gameJoinerService.joinPlayer(new GameName(gameName), joinRequest);
    }
}
