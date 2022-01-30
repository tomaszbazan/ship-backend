package pl.btsoftware.ship.game.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.game.board.PositionOnBoard;

import java.util.UUID;

@Repository
interface EventRewardRepository extends JpaRepository<EventRewardEntity, UUID> {
    EventRewardEntity findByPositionOnBoard(PositionOnBoard positionOnBoard);
}
