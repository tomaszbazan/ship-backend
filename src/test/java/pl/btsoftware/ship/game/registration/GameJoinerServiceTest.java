package pl.btsoftware.ship.game.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.fixtures.GameFixture;

import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.btsoftware.ship.game.fixtures.GameFixture.game;

@ExtendWith(MockitoExtension.class)
class GameJoinerServiceTest {
    @InjectMocks
    private GameJoinerService gameJoinerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerInGameRepository playerInGameRepository;

    @Mock
    private GameRegistrationService gameRegistrationService;

    @Mock
    private CountryFinderService countryFinderService;

    @Test
    void shouldAddPlayerToDatabase() {
        // given
        GameName gameName = new GameName("gameName");
        JoinRequest joinRequest = new JoinRequest("anyPlayer", "anyPassword", "anyGamePassword");
        when(gameRegistrationService.findGame(gameName)).thenReturn(of(game(gameName)));
        when(playerInGameRepository.save(any())).thenReturn(new PlayerInGameEntity(new PlayerEntity(joinRequest), game(gameName), Country.JAMAICA));

        // when
        gameJoinerService.joinPlayer(gameName, joinRequest);

        // then
        verify(playerRepository).save(any());
    }

    @Test
    void shouldJoinPlayerToGame() {
        // given
        GameName gameName = new GameName("gameName");
        JoinRequest joinRequest = new JoinRequest("anyPlayer", "anyPassword", "anyGamePassword");
        when(gameRegistrationService.findGame(gameName)).thenReturn(of(game(gameName)));
        when(playerInGameRepository.save(any())).thenReturn(new PlayerInGameEntity(new PlayerEntity(joinRequest), game(gameName), Country.JAMAICA));

        // when
        gameJoinerService.joinPlayer(gameName, joinRequest);

        // then
        verify(playerInGameRepository).save(any());
    }

    @Test
    void shouldThrowGameNotExistsWhenGameWasntCreated() {
        // given
        GameName gameName = new GameName("gameName");
        JoinRequest joinRequest = new JoinRequest("anyPlayer", "anyPassword", "anyGamePassword");
        when(gameRegistrationService.findGame(gameName)).thenReturn(Optional.empty());

        // when & then
        assertThrows(GameNotExistsException.class, () -> gameJoinerService.joinPlayer(gameName, joinRequest));
    }

    @Test
    void shouldThrowIncorrectGamePasswordWhenGamePasswordDidntMatchWithSaved() {
        // given
        GameName gameName = new GameName("gameName");
        JoinRequest joinRequest = new JoinRequest("anyPlayer", "anyPassword", "incorrectPassword");
        when(gameRegistrationService.findGame(gameName)).thenReturn(Optional.of(GameFixture.game(gameName, "correctPassword")));

        // when & then
        assertThrows(IncorrectGamePasswordException.class, () -> gameJoinerService.joinPlayer(gameName, joinRequest));
    }
}
