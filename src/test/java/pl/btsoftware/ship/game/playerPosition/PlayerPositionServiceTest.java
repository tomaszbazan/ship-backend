package pl.btsoftware.ship.game.playerPosition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import pl.btsoftware.ship.game.playerPosition.exception.GameNotFoundException;
import pl.btsoftware.ship.game.playerPosition.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerPositionServiceTest {
    private static final GameName GAME_NAME = new GameName(UUID.randomUUID().toString());
    private static final PlayerName PLAYER_NAME = new PlayerName(UUID.randomUUID().toString());

    @InjectMocks
    private PlayerPositionService playerPositionService;

    @Mock
    private PlayerPositionJpaRepository repository;

    @Mock
    private ApplicationEventPublisher events;

    @Test
    void shouldThrowGameNotFoundWhenGameNotExistsOnGeneratingBoard() {
        // given
        when(repository.getIdOfLastMoveForEachPlayerInGame(GAME_NAME.value())).thenReturn(emptyList());

        // when & then
        assertThrows(GameNotFoundException.class, () -> playerPositionService.positionOfAllPlayers(GAME_NAME));
    }

    @Test
    void shouldThrowPlayerNotFoundInGameWhenPlayerMakingMoveAndPlayerIsNotExists() {
        // given
        when(repository.findFirstByGameAndPlayerOrderByOccurrenceDesc(GAME_NAME, PLAYER_NAME)).thenReturn(empty());

        // when & then
        assertThrows(PlayerNotFoundInGameException.class, () -> playerPositionService.get(GAME_NAME, PLAYER_NAME));
    }
}
