package pl.btsoftware.ship.game.playerPosition;

import org.junit.jupiter.api.Test;
import pl.btsoftware.ship.shared.PositionOnBoard;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerPositionSnapshotEntityTest {

    @Test
    void shouldGoOnNewPositionIfCan() {
        // given
        PlayerPosition playerInGame = new PlayerPosition();
        playerInGame.setPositionOnBoard(new PositionOnBoard(3, 3));

        PositionOnBoard newPosition = new PositionOnBoard(4, 3);

        // when & then
//        PlayerPosition newPlayerInGame = PlayerPosition.move(playerInGame, newPosition, PlayerPosition.Round.PREPARING, "a");
//
//         then
//        assertThat(newPlayerInGame.getPositionOnBoard().getX()).isEqualTo(4);
//        assertThat(newPlayerInGame.getPositionOnBoard().getY()).isEqualTo(3);
    }
}
