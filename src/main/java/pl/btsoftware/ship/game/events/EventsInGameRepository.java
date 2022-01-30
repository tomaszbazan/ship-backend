package pl.btsoftware.ship.game.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.registration.game.GameName;

import java.util.List;

@Repository
interface EventsInGameRepository extends JpaRepository<EventInGameEntity, FieldId> {
    EventInGameEntity findByIdAndGame_Name(FieldId id, GameName gameName);
    List<EventInGameEntity> findAllByGame_Name(GameName gameName);
}
