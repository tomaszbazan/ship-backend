package pl.btsoftware.ship.game.country;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.board.PlayerSituation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.btsoftware.ship.game.country.Country.*;

@ExtendWith(MockitoExtension.class)
class CountryFinderTest {

    @Test
    void shouldReturnJamaicaIfThereIsNoPlayersInGame() {
        // given
        long numberOfPlayers = 0L;

        // when
        Country country = CountryFinder.nextFreeCountry(numberOfPlayers);

        // then
        assertThat(country).isEqualTo(Country.JAMAICA);
    }

    @Test
    void shouldReturnHaitiIfThereIsOnePlayerInGame() {
        // given
        long numberOfPlayers = 1L;

        // when
        Country country = CountryFinder.nextFreeCountry(numberOfPlayers);

        // then
        assertThat(country).isEqualTo(Country.HAITI);
    }

    @Test
    void shouldReturnCubaIfThereIsTwoPlayerInGame() {
        // given
        long numberOfPlayers = 2L;

        // when
        Country country = CountryFinder.nextFreeCountry(numberOfPlayers);

        // then
        assertThat(country).isEqualTo(CUBA);
    }

    @Test
    void shouldReturnGuatemalaIfThereIsThreePlayerInGame() {
        // given
        long numberOfPlayers = 3L;

        // when
        Country country = CountryFinder.nextFreeCountry(numberOfPlayers);

        // then
        assertThat(country).isEqualTo(GUATEMALA);
    }

    @Test
    void shouldReturnPuertoRicoIfThereIsFourPlayersInGame() {
        // given
        long numberOfPlayers = 4L;

        // when
        Country country = CountryFinder.nextFreeCountry(numberOfPlayers);

        // then
        assertThat(country).isEqualTo(PUERTO_RICO);
    }

    @Test
    void shouldThrownMaximumPlayersInGameExceptionWhenThereIsMoreThanFourPlayersInGame() {
        // given
        long numberOfPlayers = 5L;

        // when & then
        assertThrows(PlayerSituation.MaximumPlayersInGameException.class, () -> CountryFinder.nextFreeCountry(numberOfPlayers));
    }
}
