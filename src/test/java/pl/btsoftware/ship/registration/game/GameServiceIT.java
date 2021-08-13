package pl.btsoftware.ship.registration.game;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceIT extends IntegrationTest {
    @Autowired
    private GameService gameService;

    @Test
    void shouldStorePasswordEncryptedInSha256() {
        // given
        String password = "hardPassword!23aDASdzxc123))***";
        RegisterGameRequest registerGameRequest = new RegisterGameRequest("anyName" + UUID.randomUUID(), password, LocalDate.of(2020, 4, 7));

        // when
        GameName gameName = gameService.register(registerGameRequest);

        // then
        Optional<GameEntity> gameEntity = gameService.findGame(gameName);
        assertThat(gameEntity.isPresent()).isTrue();
        assertThat(gameEntity.get().getPassword()).isEqualTo(new GamePassword(password));
    }
}
