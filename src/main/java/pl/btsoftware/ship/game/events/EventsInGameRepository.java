package pl.btsoftware.ship.game.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.registration.game.GameName;

import java.util.Optional;

@Repository
interface EventsInGameRepository extends JpaRepository<EventsInGameEntity, FieldId> {
    EventsInGameEntity findByIdAndGame_Name(FieldId id, GameName gameName);
}
