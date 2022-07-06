package pl.btsoftware.ship.game.playerPosition;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.state.GameStateFixture;
import pl.btsoftware.ship.registration.GameFixture;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;
import pl.btsoftware.ship.shared.PositionOnBoard;

import static java.util.Collections.emptyList;

@Component
@AllArgsConstructor
public class PlayerPositionFixture {
    private final PlayerMoveService playerService;
    private final PlayerJoinService playerJoinService;
    private final GameFixture gameFixture;
    private final GameStateFixture gameStateFixture;

    public static PlayerPositionSnapshot createPlayer(GameName game, PlayerName player, boolean firstLogin) {
        return new PlayerPositionSnapshot(player, game, Country.JAMAICA, new PositionOnBoard(1,1), ActionType.MOVE, PlayerGoodsFixture.jamaicaStartGoods(), emptyList(), firstLogin);
    }

    public void createGameAndJoin(GameName game, PlayerName player) {
        gameFixture.startGame(game.getGame(), "any");
        playerJoinService.add(game, player);
        gameStateFixture.startGame(game);
    }

    public void movePlayer(GameName game, PlayerName player, PositionOnBoard playerPosition) {
        playerService.movePlayer(game, player, playerPosition);
    }
}
