package pl.btsoftware.ship.game.playerPosition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.game.events.EventSnapshot;
import pl.btsoftware.ship.game.events.NextActionKind;
import pl.btsoftware.ship.game.state.GameStateFixture;
import pl.btsoftware.ship.registration.GameFixture;
import pl.btsoftware.ship.registration.PlayerFixture;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;
import pl.btsoftware.ship.shared.PositionOnBoard;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static pl.btsoftware.ship.game.goods.GoodsType.*;

class PlayerMoveServiceIT extends IntegrationTest {
    @Autowired
    private PlayerMoveService playerMoveService;

    @Autowired
    private PlayerPositionService playerPositionService;

    @Autowired
    private GameFixture gameFixture;

    @Autowired
    private PlayerFixture playerFixture;

    @Autowired
    private GameStateFixture gameStateFixture;

    @Test
    public void shouldCopyGoodsFromPreviousMove() {
        // given
        GameName gameName = givenGame();
        PlayerName playerName = givenPlayer(gameName);
        givenStartGame(gameName);
        givenStartMove(gameName, playerName);

        // when
        playerMoveService.movePlayer(gameName, playerName, new PositionOnBoard(3, 2));

        // then
        PlayerPositionSnapshot playerPosition = playerPositionService.get(gameName, playerName);
        assertThat(playerPosition.goods())
                .hasSize(5)
                .extracting("type", "amount")
                .containsExactlyInAnyOrder(tuple(SUGARCANE, 100), tuple(TOBACCO, 170), tuple(CITRUS, 80), tuple(TEA, 280), tuple(GOLD, 163));
    }

    @Test
    public void shouldReturnNextActionIfExists() {
        // given
        GameName gameName = givenGame();
        PlayerName playerName = givenPlayer(gameName);
        givenStartGame(gameName);
        givenStartMove(gameName, playerName);

        // when
        Optional<EventSnapshot.EventDescription> eventDescription = playerMoveService.movePlayer(gameName, playerName, new PositionOnBoard(4, 2));

        // then
        assertThat(eventDescription).isPresent().map(EventSnapshot.EventDescription::action).hasValue(NextActionKind.QUESTION);
    }

    private void givenStartMove(GameName gameName, PlayerName playerName) {
        playerMoveService.movePlayer(gameName, playerName, new PositionOnBoard(4, 1));
    }

    private void givenStartGame(GameName gameName) {
        gameStateFixture.startGame(gameName);
    }

    private PlayerName givenPlayer(GameName gameName) {
        return playerFixture.joinPlayer(gameName, new PlayerName("any" + UUID.randomUUID()), "any", "any").playerName();
    }

    private GameName givenGame() {
        return gameFixture.startGame("any" + UUID.randomUUID(), "any");
    }

}
