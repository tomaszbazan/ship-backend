package pl.btsoftware.ship.registration;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.btsoftware.ship.shared.GameName;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@Slf4j
class RegisterGameRestController {

    private final GameService gameService;

    @PostMapping(value = "/game")
    @ResponseStatus(HttpStatus.CREATED)
    GameName registerGame(@Valid @RequestBody RegisterGameRequest gameRegisterRequest) {
        return gameService.register(gameRegisterRequest);
    }

    record RegisterGameRequest (
        @NotNull GameName game,
        @NotEmpty String password,
        @NotNull Integer roundTime) {
    }
}
