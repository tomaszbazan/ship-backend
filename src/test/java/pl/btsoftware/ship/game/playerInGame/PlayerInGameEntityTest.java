package pl.btsoftware.ship.game.playerInGame;

import org.junit.jupiter.api.Test;
import pl.btsoftware.ship.creators.EventsCreator;
import pl.btsoftware.ship.creators.GameCreator;
import pl.btsoftware.ship.game.events.EventReward;
import pl.btsoftware.ship.game.goods.GoodsEntity;
import pl.btsoftware.ship.game.goods.GoodsType;
import pl.btsoftware.ship.game.playerInGame.exception.InvalidStartPointException;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.playerInGame.exception.InvalidMoveException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.btsoftware.ship.game.goods.GoodsType.COCOA;
import static pl.btsoftware.ship.game.goods.GoodsType.GOLD;

class PlayerInGameEntityTest {

    @Test
    void shouldGoOnNewPositionIfCan() {
        // given
        PlayerInGameEntity playerInGame = new PlayerInGameEntity();
        playerInGame.setPositionOnBoard(new PositionOnBoard(3, 3));

        PositionOnBoard newPosition = new PositionOnBoard(4, 3);

        // when & then
        PlayerInGameEntity newPlayerInGame = PlayerInGameEntity.from(playerInGame, newPosition);

        // then
        assertThat(newPlayerInGame.getPositionOnBoard().getX()).isEqualTo(4);
        assertThat(newPlayerInGame.getPositionOnBoard().getY()).isEqualTo(3);
    }
}
