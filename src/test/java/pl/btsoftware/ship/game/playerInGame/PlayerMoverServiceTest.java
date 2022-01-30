package pl.btsoftware.ship.game.playerInGame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.btsoftware.ship.creators.EventsCreator;
import pl.btsoftware.ship.game.events.EventReward;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.game.events.NextActionKind;
import pl.btsoftware.ship.game.events.SpecialFieldKind;
import pl.btsoftware.ship.game.goods.Goods;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.creators.GameCreator;
import pl.btsoftware.ship.creators.PlayerInGameCreator;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.playerInGame.validators.WeightCorrectnessValidator;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static pl.btsoftware.ship.game.events.NextActionKind.*;
import static pl.btsoftware.ship.game.events.SpecialFieldKind.*;

@ExtendWith(SpringExtension.class)
class PlayerMoverServiceTest {
    @InjectMocks
    private PlayerMoverService playerMoverService;

    @Mock
    private PlayerInGameRepository playerInGameRepository;

    @Mock
    private EventsService eventsService;

    @Mock
    private GoodsService goodsService;

    @Mock
    private WeightCorrectnessValidator weightCorrectnessValidator;

    private static Stream<Arguments> correctMoves() {
        return Stream.of(
                Arguments.of(null, new PositionOnBoard(3, 1)),
                Arguments.of(null, new PositionOnBoard(5, 1)),
                Arguments.of(null, new PositionOnBoard(8, 1)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(2, 2)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(3, 2)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(4, 2)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(2, 3)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(4, 3)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(2, 4)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(3, 4)),
                Arguments.of(new PositionOnBoard(3, 3), new PositionOnBoard(4, 4))
        );
    }

    @ParameterizedTest
    @MethodSource("correctMoves")
    public void shouldSaveCorrectMove(PositionOnBoard startPosition, PositionOnBoard moveOn) {
        // given
        GameName gameName = new GameName("anyName");
        PlayerName playerName = new PlayerName("anyPlayerName");
        when(playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName)).thenReturn(PlayerInGameCreator.createPlayer(GameCreator.game(gameName), playerName, Country.JAMAICA, startPosition));
        when(eventsService.findEvent(moveOn, gameName)).thenReturn(new EventReward(moveOn, new ArrayList<>(), new HashMap<>(), null));

        // when
        playerMoverService.movePlayer(gameName, playerName, moveOn);

        // then
        verify(playerInGameRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowPlayerNotFoundInGameWhenPlayerMakingMoveAndPlayerIsNotExists() {
        // given
        GameName gameName = new GameName("anyName");
        PlayerName playerName = new PlayerName("anyPlayerName");
        PositionOnBoard positionOnBoard = new PositionOnBoard(1, 1);
        when(playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName)).thenReturn(null);

        // when & then
        assertThrows(PlayerNotFoundInGameException.class, () -> playerMoverService.movePlayer(gameName, playerName, positionOnBoard));
    }

    @Test
    void shouldReturnEventFromField() {
        // given
        SpecialFieldKind adventure = ADVENTURE;
        NextActionKind dice = DICE;
        PositionOnBoard newPosition = new PositionOnBoard(1, 2);
        GameName gameName = new GameName("anyName");
        PlayerName playerName = new PlayerName("anyPlayerName");
        when(playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName)).thenReturn(PlayerInGameCreator.createPlayer(GameCreator.game(gameName), playerName, Country.JAMAICA, new PositionOnBoard(1,1)));
        when(eventsService.findEvent(newPosition, gameName)).thenReturn(new EventReward(newPosition, new ArrayList<>(), new HashMap<>(), new PlayerNextActionDto("any", "any", dice, adventure)));

        // when
        PlayerNextActionDto nextAction = playerMoverService.movePlayer(gameName, playerName, newPosition);

        // then
        assertThat(nextAction.title()).isNotNull();
        assertThat(nextAction.description()).isNotNull();
        assertThat(nextAction.action()).isEqualTo(dice);
        assertThat(nextAction.type()).isEqualTo(adventure);
    }

    @Test
    void shouldReturnOnlyTypeWhenFoundBottleInEvent() {
        // given
        PositionOnBoard newPosition = new PositionOnBoard(1, 2);
        GameName gameName = new GameName("anyName");
        PlayerName playerName = new PlayerName("anyPlayerName");
        when(playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName)).thenReturn(PlayerInGameCreator.createPlayer(GameCreator.game(gameName), playerName, Country.JAMAICA, new PositionOnBoard(1,1)));
        when(eventsService.findEvent(newPosition, gameName)).thenReturn(new EventReward(newPosition, new ArrayList<>(), new HashMap<>(), new PlayerNextActionDto("any", "any", DICE, BOTTLE)));

        // when
        PlayerNextActionDto nextAction = playerMoverService.movePlayer(gameName, playerName, newPosition);

        // then
        assertThat(nextAction.type()).isEqualTo(BOTTLE);
        assertThat(nextAction.title()).isNull();
        assertThat(nextAction.description()).isNull();
        assertThat(nextAction.action()).isNull();
    }
}
