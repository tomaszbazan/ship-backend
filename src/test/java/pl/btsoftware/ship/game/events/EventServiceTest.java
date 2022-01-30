package pl.btsoftware.ship.game.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.creators.EventsCreator;
import pl.btsoftware.ship.creators.GameCreator;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;

import java.util.Collections;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @InjectMocks
    EventsService eventsService;

    @Mock
    EventsRepository eventsRepository;

    @Mock
    EventsInGameRepository eventsInGameRepository;

    @Mock
    EventRewardRepository eventRewardRepository;

    @Test
    void shouldCopyAllRowsFormEventsTableToEventsInGame() {
        // given
        GameEntity newGame = GameCreator.game(new GameName("any"));
        when(eventsRepository.findAll()).thenReturn(EventsCreator.defaultEvents());

        // when
        eventsService.copyAllToGame(newGame);

        // then
        verify(eventsInGameRepository).saveAll(anyList());
    }

    @Test
    void shouldNotDeleteEventWhenIsNotRemovable() {
        // given
        GameName gameName = new GameName("any");
        PositionOnBoard positionOnBoard = new PositionOnBoard(1,1);
        when(eventsInGameRepository.findByIdAndGame_Name(any(), any())).thenReturn(notRemovableEvent());
        when(eventRewardRepository.findByPositionOnBoard(any())).thenReturn(anyReward(positionOnBoard));

        // when
        eventsService.findReward(positionOnBoard, gameName);

        // then
        verify(eventsInGameRepository, times(0)).delete(any());
    }

    @Test
    void shouldDeleteEventWhenIsRemovable() {
        // given
        GameName gameName = new GameName("any");
        PositionOnBoard positionOnBoard = new PositionOnBoard(1,1);
        when(eventsInGameRepository.findByIdAndGame_Name(any(), any())).thenReturn(removableEvent());
        when(eventRewardRepository.findByPositionOnBoard(any())).thenReturn(anyReward(positionOnBoard));

        // when
        eventsService.findReward(positionOnBoard, gameName);

        // then
        verify(eventsInGameRepository).delete(any());
    }

    private EventRewardEntity anyReward(PositionOnBoard positionOnBoard) {
        return new EventRewardEntity(UUID.randomUUID(), positionOnBoard, emptyList(), emptyList());
    }

    private EventInGameEntity removableEvent() {
        return new EventInGameEntity(new FieldId(1,1), GameCreator.game(), SpecialFieldKind.BOTTLE, "any", "any", null, true);
    }

    private EventInGameEntity notRemovableEvent() {
        return new EventInGameEntity(new FieldId(1,1), GameCreator.game(), SpecialFieldKind.BOTTLE, "any", "any", null, false);
    }
}
