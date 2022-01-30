package pl.btsoftware.ship.game.playerInGame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.creators.GameCreator;
import pl.btsoftware.ship.creators.GoodsCreator;
import pl.btsoftware.ship.creators.PlayerCreator;
import pl.btsoftware.ship.creators.PlayerInGameCreator;
import pl.btsoftware.ship.game.goods.Goods;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerEntity;
import pl.btsoftware.ship.registration.player.PlayerName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.btsoftware.ship.game.goods.GoodsType.CITRUS;

@ExtendWith(MockitoExtension.class)
class PlayerJoinToGameServiceTest {
    @InjectMocks
    PlayerJoinToGameService playerJoinToGameService;

    @Mock
    PlayerInGameRepository playerInGameRepository;

    @Mock
    GoodsService goodsService;

    @Test
    void shouldAddBeginningGoodsToDatabaseAndReturnThem() {
        // given
        GameEntity game = GameCreator.game(new GameName("any"));
        PlayerEntity player = PlayerCreator.player(new PlayerName("any"));
        when(playerInGameRepository.countPlayersInGame(game.getName().getName())).thenReturn(0L);
        when(goodsService.createStartGoodsFor(any(), any())).thenReturn(GoodsCreator.jamaicaStartGoodsEntity());
        when(playerInGameRepository.save(any())).thenReturn(PlayerInGameCreator.createPlayer(game.getName(), player.getName()));

        // when
        PlayerInGame playerInGame = playerJoinToGameService.add(game, player);

        // then
        assertThat(playerInGame.getGoods()).isNotEmpty();
        assertThat(playerInGame.getGoods()).extracting(Goods::type, Goods::amount).containsExactly(tuple(CITRUS, 10));
    }
}
