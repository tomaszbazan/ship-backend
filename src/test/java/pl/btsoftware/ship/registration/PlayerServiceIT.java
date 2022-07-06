package pl.btsoftware.ship.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionSnapshot;
import pl.btsoftware.ship.registration.RegisterPlayerRestController.RegisterPlayerRequest;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

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

    @Autowired
    private PlayerPositionService playerPositionService;

    @Test
    void shouldStorePasswordEncryptedInPbkdf2() {
        // given
        String password = "hardPassword!23aDASdzxc123))***";
        String gamePassword = "anyGamePassword";
        GameName gameName = new GameName("anyGame" + UUID.randomUUID());
        gameFixture.startGame(gameName.value(), gamePassword);
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(new PlayerName("anyName"), password, gamePassword);

        // when
        PlayerPositionSnapshot playerPositionSnapshot = playerService.joinPlayer(gameName, registerPlayerRequest);

        // then
        Optional<PlayerEntity> player = playerRepository.findByName(playerPositionSnapshot.playerName());
        assertThat(player).isPresent();
        assertThat(passwordEncoder.matches(password, player.get().getPassword().value())).isTrue();
    }

    @Test
    void upTo5PlayersCanJoinToGame() {
        // given
        GameName gameName = new GameName("anyGame" + UUID.randomUUID());
        String gamePassword = "anyGamePassword";
        gameFixture.startGame(gameName.value(), gamePassword);

        // when
        for(int i = 0; i < 5; i++) {
            String password = "anyPassword";
            RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(new PlayerName("anyName" + i), password, gamePassword);
            playerService.joinPlayer(gameName, registerPlayerRequest);
        }

        // then
        assertThat(playerPositionService.numberOfPlayersInGame(gameName)).isEqualTo(5);
    }
}
