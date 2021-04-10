package pl.btsoftware.ship.game.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void positionYCantBeMoreThan8() {
        // given
        int x = 5;
        int y = 9;

        // when & then
        assertThrows(IncorrectPositionException.class, () -> new PositionOnBoard(x, y));
    }
}
