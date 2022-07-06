package pl.btsoftware.ship.game.playerPosition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerJoinServiceIT extends IntegrationTest {

    @Autowired
    private PlayerJoinService playerJoinService;

    @Test
    void shouldCreatePlayerWithStartingGoods() {
        // given
        GameName game = new GameName("any");
        PlayerName player = new PlayerName("any");

        // when
        PlayerPositionSnapshot position = playerJoinService.add(game, player);

        // then
        assertThat(position.goods()).isNotEmpty();

    }

}
