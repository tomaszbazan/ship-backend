package pl.btsoftware.ship.game.playerInGame;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.events.NextActionKind;
import pl.btsoftware.ship.game.goods.Goods;
import pl.btsoftware.ship.registration.game.GameFixture;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerFixture;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.btsoftware.ship.game.goods.GoodsType.COTTON;

class PlayerMoverServiceIT extends IntegrationTest {
    @Autowired
    private PlayerMoverService playerMoverService;

    @Autowired
    private PlayerInGameService playerInGameService;

    @Autowired
    private GameFixture gameFixture;

    @Autowired
    private PlayerFixture playerFixture;

    @Disabled
    @Test
    public void shouldAddGoodsOnNewEvent() {
        // given
        GameName gameName = givenGame();
        PlayerName playerName = givenPlayer(gameName);
        givenStartMove(gameName, playerName);

        // when
        playerMoverService.movePlayer(gameName, playerName, new PositionOnBoard(3, 2));

        // then
        PlayerInGame playerInGame = playerInGameService.findPlayerInGame(gameName, playerName);
        assertThat(playerInGame.goods).hasSize(6);
        Optional<Integer> gold = playerInGame.goods.stream().filter(goods -> goods.type().equals(COTTON)).map(Goods::amount).findFirst();
        assertThat(gold).isPresent();
        assertThat(gold.get()).isEqualTo(10);
    }

    @Test
    public void shouldReturnNextActionIfExists() {
        // given
        GameName gameName = givenGame();
        PlayerName playerName = givenPlayer(gameName);
        givenStartMove(gameName, playerName);

        // when
        PlayerNextActionDto playerNextActionDto = playerMoverService.movePlayer(gameName, playerName, new PositionOnBoard(4, 2));

        // then
        assertThat(playerNextActionDto.action()).isEqualTo(NextActionKind.QUESTION);
    }

    private void givenStartMove(GameName gameName, PlayerName playerName) {
        playerMoverService.movePlayer(gameName, playerName, new PositionOnBoard(4, 1));
    }

    private PlayerName givenPlayer(GameName gameName) {
        return playerFixture.joinPlayer(gameName, "any" + UUID.randomUUID(), "any", "any").playerName;
    }

    private GameName givenGame() {
        return gameFixture.createGame("any" + UUID.randomUUID(), "any");
    }

}
