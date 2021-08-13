package pl.btsoftware.ship.registration.player;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.game.playerInGame.PlayerInGame;
import pl.btsoftware.ship.registration.game.GameFixture;
import pl.btsoftware.ship.registration.game.GameName;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerServiceIT extends IntegrationTest {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameFixture gameFixture;

    @Test
    void shouldStorePasswordEncryptedInSha256() {
        // given
        String password = "hardPassword!23aDASdzxc123))***";
        String gamePassword = "anyGamePassword";
        GameName gameName = new GameName("anyGame" + UUID.randomUUID());
        gameFixture.createGame(gameName.getName(), gamePassword);
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest("anyName", password, gamePassword);

        // when
        PlayerInGame playerInGame = playerService.joinPlayer(gameName, registerPlayerRequest);

        // then
        Optional<PlayerEntity> gameEntity = playerRepository.findByName(playerInGame.getPlayerName());
        assertThat(gameEntity.isPresent()).isTrue();
        assertThat(gameEntity.get().getPassword()).isEqualTo(new PlayerPassword(password));
    }
}
