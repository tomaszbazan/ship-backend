package pl.btsoftware.ship.game.playerPosition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.events.*;
import pl.btsoftware.ship.game.events.EventSnapshot.EventDescription;
import pl.btsoftware.ship.game.playerPosition.validators.GameValidator;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;
import pl.btsoftware.ship.shared.PositionOnBoard;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static pl.btsoftware.ship.game.events.NextActionKind.DICE;
import static pl.btsoftware.ship.game.events.SpecialFieldKind.ADVENTURE;
import static pl.btsoftware.ship.game.events.SpecialFieldKind.BOTTLE;

@ExtendWith(MockitoExtension.class)
class PlayerMoveServiceTest {
    public static final GameName GAME_NAME = new GameName("anyName");
    public static final PlayerName PLAYER_NAME = new PlayerName("anyPlayerName");
    public static final PositionOnBoard NEXT_POSITION = new PositionOnBoard(1, 2);

    @InjectMocks
    private PlayerMoveService playerMoveService;

    @Mock
    private PlayerPositionService playerPositionService;

    @Mock
    private EventsService eventsService;

    @Mock
    private GameValidator gameValidator;

    @Mock
    private EventSelector eventSelector;

    @Test
    void shouldMakeMoveIfCan() {
        // given
        givenEmptyEvent();

        // when
        playerMoveService.movePlayer(GAME_NAME, PLAYER_NAME, NEXT_POSITION);

        // then
        verify(gameValidator).canMove(GAME_NAME);
        verify(playerPositionService).moveOn(GAME_NAME, PLAYER_NAME, NEXT_POSITION);
    }

    @Test
    void shouldReturnEventFromField() {
        // given
        SpecialFieldKind adventure = ADVENTURE;
        NextActionKind dice = DICE;
        when(eventsService.findEvent(NEXT_POSITION, GAME_NAME)).thenReturn(Optional.of(new EventSnapshot(new EventDescription("any", "any", dice, adventure))));

        // when
        Optional<EventDescription> nextAction = playerMoveService.movePlayer(GAME_NAME, PLAYER_NAME, NEXT_POSITION);

        // then
        assertThat(nextAction).isPresent().map(EventSnapshot.EventDescription::title).isNotNull();
        assertThat(nextAction).isPresent().map(EventSnapshot.EventDescription::description).isNotNull();
        assertThat(nextAction).isPresent().map(EventSnapshot.EventDescription::action).hasValue(dice);
        assertThat(nextAction).isPresent().map(EventSnapshot.EventDescription::type).hasValue(adventure);
    }

    @Test
    void shouldReturnOnlyTypeWhenFoundBottleInEvent() {
        // given
        when(eventsService.findEvent(NEXT_POSITION, GAME_NAME)).thenReturn(Optional.of(new EventSnapshot(new EventDescription("any", "any", DICE, BOTTLE))));

        // when
        Optional<EventDescription> nextAction = playerMoveService.movePlayer(GAME_NAME, PLAYER_NAME, NEXT_POSITION);

        // then
        assertThat(nextAction).isPresent().map(EventSnapshot.EventDescription::type).hasValue(BOTTLE);
        assertThat(nextAction).isPresent().map(EventSnapshot.EventDescription::title).isEmpty();
        assertThat(nextAction).isPresent().map(EventSnapshot.EventDescription::description).isEmpty();
        assertThat(nextAction).isPresent().map(EventSnapshot.EventDescription::action).isEmpty();
    }

    private void givenEmptyEvent() {
        when(eventsService.findEvent(NEXT_POSITION, GAME_NAME)).thenReturn(empty());
    }
}
