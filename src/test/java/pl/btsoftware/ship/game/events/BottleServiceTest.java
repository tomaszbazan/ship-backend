package pl.btsoftware.ship.game.events;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.game.events.exception.EventNotFoundException;
import pl.btsoftware.ship.game.playerPosition.ActionType;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionFixture;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionSnapshot;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;
import pl.btsoftware.ship.shared.PositionOnBoard;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.btsoftware.ship.game.goods.GoodsType.*;

class BottleServiceTest extends IntegrationTest {
    @Autowired
    BottleService bottleService;

    @Autowired
    PlayerPositionFixture playerPositionFixture;

    @Autowired
    PlayerPositionService playerPositionService;

    @Test
    void shouldThrowExceptionWhenPlayerArentFoundBottle() {
        // given
        GameName game = new GameName(UUID.randomUUID().toString());
        PlayerName player = new PlayerName(UUID.randomUUID().toString());
        playerPositionFixture.createGameAndJoin(game, player);
        playerPositionFixture.movePlayer(game, player, new PositionOnBoard(1, 1));

        // when & then
        assertThrows(EventNotFoundException.class, () -> bottleService.accept(game, player));
    }

    @Test
    void shouldSavePlayerMove() {
        // given
        GameName game = new GameName(UUID.randomUUID().toString());
        PlayerName player = new PlayerName(UUID.randomUUID().toString());
        playerPositionFixture.createGameAndJoin(game, player);
        playerPositionFixture.movePlayer(game, player, new PositionOnBoard(3, 1));
        playerPositionFixture.movePlayer(game, player, new PositionOnBoard(3, 2));

        // when
        bottleService.accept(game, player);

        // then
        assertThat(playerPositionService.get(game, player).lastAction()).isEqualTo(ActionType.MOVE);
    }

    @Test
    void shouldRemove5GoodAndAddGoodFoundInBottleToPlayersAccount() {
        // given
        GameName game = new GameName(UUID.randomUUID().toString());
        PlayerName player = new PlayerName(UUID.randomUUID().toString());
        playerPositionFixture.createGameAndJoin(game, player);
        playerPositionFixture.movePlayer(game, player, new PositionOnBoard(3, 1));
        playerPositionFixture.movePlayer(game, player, new PositionOnBoard(3, 2));

        // when
        bottleService.accept(game, player);

        // then
        PlayerPositionSnapshot playerSituationAfterAction = playerPositionService.get(game, player);
        assertThat(playerSituationAfterAction.goods()).hasSize(6)
                .extracting("type", "amount")
                .containsExactlyInAnyOrder(
                        tuple(GOLD, 158),
                        tuple(SUGARCANE, 100),
                        tuple(COTTON, 10),
                        tuple(TOBACCO, 170),
                        tuple(TEA, 280),
                        tuple(CITRUS, 80)
                );
    }
}
