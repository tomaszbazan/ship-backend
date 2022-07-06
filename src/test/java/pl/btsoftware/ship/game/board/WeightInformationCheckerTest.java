package pl.btsoftware.ship.game.board;

import org.junit.jupiter.api.Test;
import pl.btsoftware.ship.shared.PositionOnBoard;

import static org.assertj.core.api.Assertions.assertThat;

class WeightInformationCheckerTest {
    @Test
    void shouldFindCorrectWeightOfField() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(1, 1);

        // when
        int weight = WeightInformationChecker.fieldMaximumWeight(positionOnBoard);

        // then
        assertThat(weight).isEqualTo(770);
    }
}
