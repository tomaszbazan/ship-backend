package pl.btsoftware.ship.registration.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.btsoftware.ship.creators.GameCreator;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.registration.game.exception.GameAlreadyExistsException;

import java.time.LocalDate;
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

    @Test
    void shouldSaveGameRequestInDatabase() {
        // given
        RegisterGameRequest registerGameRequest = RegisterGameRequest.builder().gameName(GAME_NAME.getName()).password(GAME_PASSWORD).startDate(LocalDate.MAX).build();
        when(gameRepository.save(any())).thenReturn(GameCreator.game(GAME_NAME, GAME_PASSWORD));

        // when
        GameName gameNameResult = gameService.register(registerGameRequest);

        // then
        verify(gameRepository).save(any());
        assertThat(gameNameResult.getName()).isEqualTo(GAME_NAME.getName());
    }

    @Test
    void shouldCopyTableWithEvents() {
        // given
        RegisterGameRequest registerGameRequest = RegisterGameRequest.builder().gameName(GAME_NAME.getName()).password(GAME_PASSWORD).startDate(LocalDate.MAX).build();
        GameEntity newGame = GameCreator.game(GAME_NAME, GAME_PASSWORD);
        when(gameRepository.save(any())).thenReturn(newGame);

        // when
        gameService.register(registerGameRequest);

        // then
        verify(eventsService).copyAllToGame(newGame);
    }

    @Test
    void shouldThrowExceptionWhenGameNameAlreadyExists() {
        // given
        RegisterGameRequest registerGameRequest = RegisterGameRequest.builder().gameName(GAME_NAME.getName()).password(GAME_PASSWORD).startDate(LocalDate.MAX).build();
        when(gameRepository.findByName(GAME_NAME)).thenReturn(Optional.of(GameCreator.game(GAME_NAME, GAME_PASSWORD)));

        // when & then
        assertThrows(GameAlreadyExistsException.class, () -> gameService.register(registerGameRequest));
    }
}
