package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.events.exception.EventNotFoundException;
import pl.btsoftware.ship.game.playerPosition.PlayerMoveService;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PositionOnBoard;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;
    private final EventsInGameRepository eventsInGameRepository;
    private final EventRewardRepository eventRewardRepository;

    public List<EventFieldDto> findAllInGame(GameName gameName) {
        return eventsInGameRepository.findAllById_Game(gameName).stream().map(event -> new EventFieldDto(event.mapId(), event.getType())).toList();
    }

    public void copyAllToGame(GameName newGame) {
        List<EventEntity> events = eventsRepository.findAll();
        List<EventInGameEntity> eventsInGameEntities = events.stream().map(event -> EventInGameEntity.from(event, newGame)).toList();
        eventsInGameRepository.saveAll(eventsInGameEntities);
    }

    @Transactional(readOnly = true)
    public Optional<EventSnapshot> findEvent(PositionOnBoard playerPosition, GameName game) {
        return eventsInGameRepository.findById(FieldGameId.from(game, playerPosition))
                .map(EventSnapshot::from);
    }

    @Transactional(readOnly = true)
    EventDescription findEventDescription(PositionOnBoard playerPosition) {
        return eventsRepository.findById(FieldId.from(playerPosition)).map(EventDescription::from).orElseThrow(EventNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public EventRewardSnapshot findReward(PositionOnBoard playerPosition) {
        return eventRewardRepository.findByPositionOnBoard(playerPosition)
                .map(EventRewardSnapshot::from)
                .orElse(EventRewardSnapshot.from(playerPosition));
    }

    void removeReward(GameName game, PositionOnBoard position) {
        eventsInGameRepository.deleteById(FieldGameId.from(game, position));
    }

    record EventDescription(String title, String description) {
        static EventDescription from(EventEntity event) {
            return new EventDescription(event.getTitle(), event.getDescription());
        }
    }
}
