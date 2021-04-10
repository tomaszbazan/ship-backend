package pl.btsoftware.ship.game.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pl.btsoftware.ship.game.registration.Country.*;

@ExtendWith(MockitoExtension.class)
class CountryFinderServiceTest {
    @InjectMocks
    private CountryFinderService countryFinderService;

    @Mock
    private PlayerInGameRepository playerInGameRepository;

    @Test
    void shouldReturnJamaicaIfThereIsNoPlayersInGame() {
        // given
        GameName gameName = new GameName("any");
        when(playerInGameRepository.countByGame_Name(gameName)).thenReturn(0L);

        // when
        Country country = countryFinderService.findNextFreeCountry(gameName);

        // then
        assertThat(country).isEqualTo(Country.JAMAICA);
    }

    @Test
    void shouldReturnHaitiIfThereIsOnePlayerInGame() {
        // given
        GameName gameName = new GameName("any");
        when(playerInGameRepository.countByGame_Name(gameName)).thenReturn(1L);

        // when
        Country country = countryFinderService.findNextFreeCountry(gameName);

        // then
        assertThat(country).isEqualTo(Country.HAITI);
    }

    @Test
    void shouldReturnCubaIfThereIsTwoPlayerInGame() {
        // given
        GameName gameName = new GameName("any");
        when(playerInGameRepository.countByGame_Name(gameName)).thenReturn(2L);

        // when
        Country country = countryFinderService.findNextFreeCountry(gameName);

        // then
        assertThat(country).isEqualTo(CUBA);
    }

    @Test
    void shouldReturnGuatemalaIfThereIsThreePlayerInGame() {
        // given
        GameName gameName = new GameName("any");
        when(playerInGameRepository.countByGame_Name(gameName)).thenReturn(3L);

        // when
        Country country = countryFinderService.findNextFreeCountry(gameName);

        // then
        assertThat(country).isEqualTo(GUATEMALA);
    }

    @Test
    void shouldReturnPuertoRicoIfThereIsFourPlayersInGame() {
        // given
        GameName gameName = new GameName("any");
        when(playerInGameRepository.countByGame_Name(gameName)).thenReturn(4L);

        // when
        Country country = countryFinderService.findNextFreeCountry(gameName);

        // then
        assertThat(country).isEqualTo(PUERTO_RICO);
    }

    @Test
    void shouldThrownMaximumPlayersInGameExceptionWhenThereIsMoreThanFourPlayersInGame() {
        // given
        GameName gameName = new GameName("any");
        when(playerInGameRepository.countByGame_Name(gameName)).thenReturn(5L);

        // when & then
        assertThrows(MaximumPlayersInGameException.class, () -> countryFinderService.findNextFreeCountry(gameName));
    }
}
