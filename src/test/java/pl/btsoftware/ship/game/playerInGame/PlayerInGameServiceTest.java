package pl.btsoftware.ship.game.playerInGame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.creators.GameCreator;
import pl.btsoftware.ship.creators.GoodsCreator;
import pl.btsoftware.ship.creators.PlayerCreator;
import pl.btsoftware.ship.creators.PlayerInGameCreator;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerEntity;
import pl.btsoftware.ship.registration.player.PlayerName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerInGameServiceTest {
    @InjectMocks
    PlayerInGameService playerInGameService;

    @Mock
    PlayerInGameRepository playerInGameRepository;

    @Mock
    GoodsService goodsService;

    @Test
    void shouldAddBeginningGoodsToDatabaseAndReturnThem() {
        // given
        GameEntity game = GameCreator.game(new GameName("any"));
        PlayerEntity player = PlayerCreator.player(new PlayerName("any"));
        Country country = Country.JAMAICA;
        when(playerInGameRepository.countPlayersInGame(game.getName().getName())).thenReturn(0L);
        when(goodsService.findStartGoodsFor(country)).thenReturn(GoodsCreator.jamaicaStartGoods());
        when(playerInGameRepository.save(any())).thenReturn(PlayerInGameCreator.createPlayer(game.getName(), player.getName()));

        // when
        PlayerInGame playerInGame = playerInGameService.addPlayerToGame(game, player);

        // then
        assertThat(playerInGame.getGoods()).isNotEmpty();
        assertThat(playerInGame.getGoods()).containsExactlyInAnyOrderElementsOf(GoodsCreator.jamaicaStartGoods());
    }
}
