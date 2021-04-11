package pl.btsoftware.ship.game.registration;

import org.apache.commons.codec.digest.DigestUtils;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class GameRegistrationServiceIT extends IntegrationTest {
    @Autowired
    private GameRegistrationService gameRegistrationService;

    @Test
    void shouldStorePasswordEncryptedInSha256() {
        // given
        String password = "hardPassword!23aDASdzxc123))***";
        GameRequest gameRequest = new GameRequest("anyName", password, LocalDate.of(2020, 4, 7));

        // when
        GameName gameName = gameRegistrationService.register(gameRequest);

        // then
        Optional<GameEntity> gameEntity = gameRegistrationService.findGame(gameName);
        assertThat(gameEntity.isPresent()).isTrue();
        assertThat(gameEntity.get().getPassword()).isNotEqualTo(password);
        assertThat(gameEntity.get().getPassword()).isEqualTo(DigestUtils.sha256Hex(password));
    }
}
