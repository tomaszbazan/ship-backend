package pl.btsoftware.ship.registration.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    void shouldSaveGameRequestInDatabase() {
        // given
        String gameName = "any";
        RegisterGameRequest registerGameRequest = RegisterGameRequest.builder().gameName(gameName).password("any").startDate(LocalDate.MAX).build();
        when(gameRepository.save(any())).thenReturn(GameEntity.from(registerGameRequest));

        // when
        GameName gameNameResult = gameService.register(registerGameRequest);

        // then
        verify(gameRepository).save(any());
        assertThat(gameNameResult.getName()).isEqualTo(gameName);
    }

    @Test
    void shouldThrowExceptionWhenGameNameAlreadyExists() {
        // given
        RegisterGameRequest registerGameRequest = RegisterGameRequest.builder().gameName("any").password("any").startDate(LocalDate.MAX).build();
        when(gameRepository.findByName(new GameName(registerGameRequest.getGameName()))).thenReturn(Optional.of(GameEntity.from(registerGameRequest)));

        // when & then
        assertThrows(GameAlreadyExistsException.class, () -> gameService.register(registerGameRequest));
    }
}
