package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;
    private final EventsInGameRepository eventsInGameRepository;

    public void copyAllToGame(GameEntity newGame) {
        List<Events> events = eventsRepository.findAll();
        List<EventsInGameEntity> eventsInGameEntities = events.stream().map(event -> EventsInGameEntity.from(event, newGame)).collect(Collectors.toList());
        eventsInGameRepository.saveAll(eventsInGameEntities);
    }

    public EventsInGameEntity getEvent(PositionOnBoard playerPosition, GameName gameName) {
        EventsInGameEntity event = eventsInGameRepository.findByIdAndGame_Name(FieldId.from(playerPosition), gameName);
        if(event != null && event.isRemovable()) {
            eventsInGameRepository.delete(event);
        }

        return event;
    }
}
