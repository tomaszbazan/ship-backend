package pl.btsoftware.ship.game.playerPosition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.goods.GoodsDto;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static pl.btsoftware.ship.game.goods.GoodsType.CITRUS;
import static pl.btsoftware.ship.game.goods.GoodsType.GOLD;

class PlayerPositionServiceIT extends IntegrationTest {
    @Autowired
    PlayerPositionService playerPositionService;

    @Test
    void shouldCorrectlyPlayerInformationOnJoin() {
        // given
        GameName game = new GameName("game");
        PlayerName player = new PlayerName("player");
        Country country = Country.JAMAICA;
        List<GoodsDto> goodsToAdd = List.of(new GoodsDto(GOLD, 5), new GoodsDto(CITRUS, 10));

        // when
        playerPositionService.joinPlayer(game, player, country, goodsToAdd);

        // then
        PlayerPositionSnapshot playerPosition = playerPositionService.get(game, player);
        assertThat(playerPosition.lastAction()).isEqualTo(ActionType.JOIN);
        assertThat(playerPosition.goods()).hasSize(2)
                .extracting("type", "amount")
                .containsExactlyInAnyOrder(tuple(GOLD, 5), tuple(CITRUS, 10));
    }
}
