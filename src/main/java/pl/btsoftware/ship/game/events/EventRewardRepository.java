package pl.btsoftware.ship.game.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.shared.PositionOnBoard;

import java.util.Optional;
import java.util.UUID;

@Repository
interface EventRewardRepository extends JpaRepository<EventRewardEntity, UUID> {
    Optional<EventRewardEntity> findByPositionOnBoard(PositionOnBoard positionOnBoard);
}
