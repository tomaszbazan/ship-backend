package pl.btsoftware.ship.game.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.shared.GameName;

import java.util.List;

@Repository
interface EventsInGameRepository extends JpaRepository<EventInGameEntity, FieldGameId> {
    List<EventInGameEntity> findAllById_Game(GameName game);
}
