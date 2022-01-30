package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.playerInGame.PlayerNextActionDto;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;
    private final EventsInGameRepository eventsInGameRepository;
    private final EventRewardRepository eventRewardRepository;

    public List<EventField> findAllInGame(GameName gameName) {
        return eventsInGameRepository.findAllByGame_Name(gameName).stream().map(event -> new EventField(event.getId(), event.getType())).collect(Collectors.toList());
    }

    public void copyAllToGame(GameEntity newGame) {
        List<EventEntity> events = eventsRepository.findAll();
        List<EventInGameEntity> eventsInGameEntities = events.stream().map(event -> EventInGameEntity.from(event, newGame)).collect(Collectors.toList());
        eventsInGameRepository.saveAll(eventsInGameEntities);
    }

    @Transactional(readOnly = true)
    public EventReward findEvent(PositionOnBoard playerPosition, GameName gameName) {
        EventInGameEntity event = eventsInGameRepository.findByIdAndGame_Name(FieldId.from(playerPosition), gameName);
        if (event == null) {
            return EventReward.from(playerPosition);
        }
        PlayerNextActionDto playerNextAction = new PlayerNextActionDto(event.getTitle(), event.getDescription(), event.getNextAction(), event.getType());

        return EventReward.from(playerPosition, playerNextAction);
    }

    @Deprecated
    @Transactional
    public EventReward findReward(PositionOnBoard playerPosition, GameName gameName) {
        EventInGameEntity event = eventsInGameRepository.findByIdAndGame_Name(FieldId.from(playerPosition), gameName);
        if (event == null) {
            return EventReward.from(playerPosition);
        }
        PlayerNextActionDto playerNextAction = new PlayerNextActionDto(event.getTitle(), event.getDescription(), event.getNextAction(), event.getType());
        if (event.isRemovable()) {
            eventsInGameRepository.delete(event);
        }

        EventRewardEntity eventReward = eventRewardRepository.findByPositionOnBoard(playerPosition);
        return EventReward.from(playerPosition, playerNextAction, eventReward);
    }
}
