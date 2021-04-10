package pl.btsoftware.ship.game.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameRegistrationServiceTest {
    @InjectMocks
    private GameRegistrationService gameRegistrationService;

    @Mock
    private GameRepository gameRepository;

    @Test
    void shouldSaveGameRequestInDatabase() {
        // given
        String gameName = "any";
        GameRequest gameRequest = GameRequest.builder().gameName(gameName).password("any").startDate(LocalDate.MAX).build();
        when(gameRepository.save(any())).thenReturn(new GameEntity(gameRequest));

        // when
        GameName gameNameResult = gameRegistrationService.register(gameRequest);

        // then
        verify(gameRepository).save(any());
        assertThat(gameNameResult.getName()).isEqualTo(gameName);
    }

    @Test
    void shouldThrowExceptionWhenGameNameAlreadyExists() {
        // given
        GameRequest gameRequest = GameRequest.builder().gameName("any").password("any").startDate(LocalDate.MAX).build();
        when(gameRepository.findByName(new GameName(gameRequest.getGameName()))).thenReturn(Optional.of(new GameEntity(gameRequest)));

        // when & then
        assertThrows(GameAlreadyExistsException.class, () -> gameRegistrationService.register(gameRequest));
    }
}
