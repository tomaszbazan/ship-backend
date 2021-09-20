package pl.btsoftware.ship.registration.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.btsoftware.ship.creators.GameCreator;
import pl.btsoftware.ship.creators.GoodsCreator;
import pl.btsoftware.ship.creators.PlayerInGameCreator;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.playerInGame.PlayerInGame;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.GameService;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.player.exception.IncorrectPasswordException;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    private static final String CORRECT_GAME_PASSWORD = "anyGamePassword";

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerInGameService playerInGameService;

    @Mock
    private GameService gameService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldRegisterPlayerAndSaveItToDatabase() {
        // given
        GameName gameName = new GameName("gameName");
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest("anyPlayer", "anyPassword", CORRECT_GAME_PASSWORD);
        givenGameExists(gameName);
        givenPasswordsMatch();
        when(playerInGameService.addPlayerToGame(any(), any())).thenReturn(PlayerInGame.from(PlayerInGameCreator.createPlayer(gameName, new PlayerName("anyPlayer"))));

        // when
        playerService.joinPlayer(gameName, registerPlayerRequest);

        // then
        verify(playerRepository).save(any());
    }

    @Test
    void shouldThrowGameNotExistsWhenGameWasntCreated() {
        // given
        GameName gameName = new GameName("gameName");
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest("anyPlayer", "anyPassword", "anyGamePassword");
        givenGameNotExists(gameName);

        // when & then
        assertThrows(GameNotExistsException.class, () -> playerService.joinPlayer(gameName, registerPlayerRequest));
    }

    @Test
    void shouldThrowIncorrectGamePasswordWhenGamePasswordDidntMatchWithSaved() {
        // given
        GameName gameName = new GameName("gameName");
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest("anyPlayer", "anyPassword", "incorrectGamePassword");
        givenGameExists(gameName);
        givenPasswordsDoesntMatch();

        // when & then
        assertThrows(IncorrectPasswordException.class, () -> playerService.joinPlayer(gameName, registerPlayerRequest));
    }

    private void givenPasswordsMatch() {
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
    }

    private void givenPasswordsDoesntMatch() {
        when(passwordEncoder.matches(any(), any())).thenReturn(false);
    }

    private void givenGameExists(GameName gameName) {
        when(gameService.findGame(gameName)).thenReturn(of(GameCreator.game(gameName, CORRECT_GAME_PASSWORD)));
    }

    private void givenGameNotExists(GameName gameName) {
        when(gameService.findGame(gameName)).thenReturn(empty());
    }
}
