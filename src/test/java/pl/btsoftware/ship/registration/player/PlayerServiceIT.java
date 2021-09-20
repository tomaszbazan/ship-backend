package pl.btsoftware.ship.registration.player;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldStorePasswordEncryptedInPbkdf2() {
        // given
        String password = "hardPassword!23aDASdzxc123))***";
        String gamePassword = "anyGamePassword";
        GameName gameName = new GameName("anyGame" + UUID.randomUUID());
        gameFixture.createGame(gameName.getName(), gamePassword);
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest("anyName", password, gamePassword);

        // when
        PlayerInGame playerInGame = playerService.joinPlayer(gameName, registerPlayerRequest);

        // then
        Optional<PlayerEntity> player = playerRepository.findByName(playerInGame.getPlayerName());
        assertThat(player.isPresent()).isTrue();
        assertThat(passwordEncoder.matches(password, player.get().getPassword().value())).isTrue();
    }
}
