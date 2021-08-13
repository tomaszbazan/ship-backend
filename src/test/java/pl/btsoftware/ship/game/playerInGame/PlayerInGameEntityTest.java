package pl.btsoftware.ship.game.playerInGame;

import org.junit.jupiter.api.Test;
import pl.btsoftware.ship.game.playerInGame.exception.InvalidStartPointException;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.playerInGame.exception.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerInGameEntityTest {

    @Test
    public void shouldThrowInvalidStartPointExceptionIfStartPositionIsNullAndNewCoordinateIsNotInFirstLine() {
        // given
        PlayerInGameEntity playerInGameEntity = new PlayerInGameEntity();

        // when & then
        assertThrows(InvalidStartPointException.class, () -> playerInGameEntity.canMoveOn(new PositionOnBoard(1, 3)));
    }

    @Test
    public void shouldThrowInvalidMoveExceptionIfStartPositionIsNotNullAndNewCoordinateIsInMoreThanOneField() {
        // given
        PlayerInGameEntity playerInGameEntity = new PlayerInGameEntity();
        playerInGameEntity.setPositionOnBoard(new PositionOnBoard(1, 1));

        // when & then
        assertThrows(InvalidMoveException.class, () -> playerInGameEntity.canMoveOn(new PositionOnBoard(1, 3)));
    }

    @Test
    public void shouldThrowInvalidMoveExceptionIfStartPositionAndNewCoordinateAreEquals() {
        // given
        PlayerInGameEntity playerInGameEntity = new PlayerInGameEntity();
        playerInGameEntity.setPositionOnBoard(new PositionOnBoard(3, 3));

        // when & then
        assertThrows(InvalidMoveException.class, () -> playerInGameEntity.canMoveOn(new PositionOnBoard(3, 3)));
    }
}
