package pl.btsoftware.ship.game.playerPosition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.goods.GoodsCreator;
import pl.btsoftware.ship.game.goods.GoodsDto;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.btsoftware.ship.game.goods.GoodsType.CITRUS;

@ExtendWith(MockitoExtension.class)
class PlayerJoinServiceTest {
    public static final GameName GAME_NAME = new GameName("any");
    public static final PlayerName PLAYER_NAME = new PlayerName("any");

    @InjectMocks
    PlayerJoinService playerJoinService;

    @Mock
    PlayerPositionService playerPositionService;

    @Mock
    GoodsService goodsService;

    @Test
    void shouldAddBeginningGoodsToDatabaseAndReturnThem() {
        // given
        when(playerPositionService.numberOfPlayersInGame(GAME_NAME)).thenReturn(0L);
        when(goodsService.findStartGoodsFor(any())).thenReturn(GoodsCreator.jamaicaStartGoods());
        when(playerPositionService.get(GAME_NAME, PLAYER_NAME)).thenReturn(PlayerPositionFixture.createPlayer(GAME_NAME, PLAYER_NAME, true));

        // when
        PlayerPositionSnapshot playerPositionSnapshot = playerJoinService.add(GAME_NAME, PLAYER_NAME);

        // then
        assertThat(playerPositionSnapshot.goods()).isNotEmpty();
        assertThat(playerPositionSnapshot.goods()).extracting(GoodsDto::type, GoodsDto::amount).containsExactly(tuple(CITRUS, 10));
    }
}
