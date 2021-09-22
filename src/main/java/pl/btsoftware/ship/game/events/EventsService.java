package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;
    private final EventsInGameRepository eventsInGameRepository;

    public void copyAllToGame(GameEntity newGame) {
        List<Event> events = eventsRepository.findAll();
        List<EventInGameEntity> eventsInGameEntities = events.stream().map(event -> EventInGameEntity.from(event, newGame)).collect(Collectors.toList());
        eventsInGameRepository.saveAll(eventsInGameEntities);
    }

    public EventInGameEntity getEvent(PositionOnBoard playerPosition, GameName gameName) {
        EventInGameEntity event = eventsInGameRepository.findByIdAndGame_Name(FieldId.from(playerPosition), gameName);
        if(event != null && event.isRemovable()) {
            eventsInGameRepository.delete(event);
        }

        return event;
    }
}
