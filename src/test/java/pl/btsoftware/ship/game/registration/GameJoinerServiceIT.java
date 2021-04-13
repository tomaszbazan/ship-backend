package pl.btsoftware.ship.game.registration;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.game.fixtures.GameFixture;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GameJoinerServiceIT extends IntegrationTest {
    @Autowired
    private GameJoinerService gameJoinerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Test
    void shouldStorePasswordEncryptedInSha256() {
        // given
        String password = "hardPassword!23aDASdzxc123))***";
        GameName gameName = new GameName("anyGame" + UUID.randomUUID());
        gameRepository.save(GameFixture.game(gameName));
        JoinRequest joinRequest = new JoinRequest("anyName", password, "anyGamePassword");

        // when
        PlayerJoined playerJoined = gameJoinerService.joinPlayer(gameName, joinRequest);

        // then
        Optional<PlayerEntity> gameEntity = playerRepository.findByName(playerJoined.getPlayerName());
        assertThat(gameEntity.isPresent()).isTrue();
        assertThat(gameEntity.get().getPassword()).isNotEqualTo(password);
        assertThat(gameEntity.get().getPassword()).isEqualTo(DigestUtils.sha256Hex(password));
    }

}
