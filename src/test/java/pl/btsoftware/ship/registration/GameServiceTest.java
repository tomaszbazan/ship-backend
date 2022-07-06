package pl.btsoftware.ship.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.registration.RegisterGameRestController.RegisterGameRequest;
import pl.btsoftware.ship.registration.exception.GameAlreadyExistsException;
import pl.btsoftware.ship.shared.GameName;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    public static final String GAME_PASSWORD = "any";
    public static final GameName GAME_NAME = new GameName("any");
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private EventsService eventsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Test
    void shouldSaveGameRequestInDatabaseAndPublishEvent() {
        // given
        RegisterGameRequest registerGameRequest = new RegisterGameRequest(GAME_NAME, GAME_PASSWORD, 10);
        when(gameRepository.save(any())).thenReturn(GameCreator.game(GAME_NAME, GAME_PASSWORD));

        // when
        GameName gameNameResult = gameService.register(registerGameRequest);

        // then
        verify(gameRepository).save(any());
        verify(eventPublisher).publishEvent(any(GameCreated.class));
        assertThat(gameNameResult.value()).isEqualTo(GAME_NAME.value());
    }

    @Test
    void shouldCopyTableWithEvents() {
        // given
        RegisterGameRequest registerGameRequest = new RegisterGameRequest(GAME_NAME, GAME_PASSWORD, 10);
        GameEntity newGame = GameCreator.game(GAME_NAME, GAME_PASSWORD);
        when(gameRepository.save(any())).thenReturn(newGame);

        // when
        gameService.register(registerGameRequest);

        // then
        verify(eventsService).copyAllToGame(newGame.gameName());
    }

    @Test
    void shouldThrowExceptionWhenGameNameAlreadyExists() {
        // given
        RegisterGameRequest registerGameRequest = new RegisterGameRequest(GAME_NAME, GAME_PASSWORD, 10);
        when(gameRepository.findByName(GAME_NAME)).thenReturn(Optional.of(GameCreator.game(GAME_NAME, GAME_PASSWORD)));

        // when & then
        assertThrows(GameAlreadyExistsException.class, () -> gameService.register(registerGameRequest));
    }
}
