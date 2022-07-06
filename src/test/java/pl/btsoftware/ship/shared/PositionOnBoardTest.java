package pl.btsoftware.ship.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionDto;
import pl.btsoftware.ship.game.playerPosition.exception.IncorrectPositionException;
import pl.btsoftware.ship.shared.PositionOnBoard;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PositionOnBoardTest {
    @Test
    void positionXCantBeLessThan1() {
        // given
        int x = 0;
        int y = 5;

        // when & then
        assertThrows(IncorrectPositionException.class, () -> new PositionOnBoard(x, y));
    }

    @Test
    void positionXCantBeMoreThan8() {
        // given
        int x = 9;
        int y = 5;

        // when & then
        assertThrows(IncorrectPositionException.class, () -> new PositionOnBoard(x, y));
    }

    @Test
    void positionYCantBeLessThan1() {
        // given
        int x = 5;
        int y = 0;

        // when & then
        assertThrows(IncorrectPositionException.class, () -> new PositionOnBoard(x, y));
    }

    @Test
    void positionYCantBeMoreThan9() {
        // given
        int x = 5;
        int y = 10;

        // when & then
        assertThrows(IncorrectPositionException.class, () -> new PositionOnBoard(x, y));
    }
}
