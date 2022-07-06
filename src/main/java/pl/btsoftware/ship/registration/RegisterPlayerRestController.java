package pl.btsoftware.ship.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionSnapshot;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@Slf4j
class RegisterPlayerRestController {

    private final PlayerService playerService;

    @PostMapping(value = "/game/{gameName}/player")
    @ResponseStatus(HttpStatus.CREATED)
    PlayerPositionSnapshot joinGame(@PathVariable GameName gameName, @Valid @RequestBody RegisterPlayerRequest registerPlayerRequest) {
        return playerService.joinPlayer(gameName, registerPlayerRequest);
    }

    record RegisterPlayerRequest(@NotNull PlayerName player, @NotEmpty String playerPassword,
                                 @NotEmpty String gamePassword) {
    }
}
