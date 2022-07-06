package pl.btsoftware.ship.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.registration.RegisterGameRestController.RegisterGameRequest;
import pl.btsoftware.ship.shared.GameName;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceIT extends IntegrationTest {
    @Autowired
    private GameService gameService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldStorePasswordEncryptedInPbkdf2() {
        // given
        String password = "hardPassword!23aDASdzxc123))***";
        RegisterGameRequest registerGameRequest = new RegisterGameRequest(new GameName("anyName" + UUID.randomUUID()), password, 10);

        // when
        GameName gameName = gameService.register(registerGameRequest);

        // then
        Optional<GameEntity> game = gameService.findGame(gameName);
        assertThat(game).isPresent();
        assertThat(passwordEncoder.matches(password, game.get().getPassword().value())).isTrue();
    }
}
