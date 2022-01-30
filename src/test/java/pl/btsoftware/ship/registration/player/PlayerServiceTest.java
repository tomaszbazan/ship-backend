package pl.btsoftware.ship.registration.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.creators.GameCreator;
import pl.btsoftware.ship.creators.PlayerCreator;
import pl.btsoftware.ship.creators.PlayerInGameCreator;
import pl.btsoftware.ship.game.playerInGame.PlayerInGame;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.game.playerInGame.PlayerJoinToGameService;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.GameService;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.player.exception.IncorrectPasswordException;
import pl.btsoftware.ship.registration.player.exception.PlayerAlreadyExistsException;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    private static final String CORRECT_GAME_PASSWORD = "correctGamePassword";
    private static final String INCORRECT_GAME_PASSWORD = "incorrectGamePassword";
    private static final String CORRECT_PLAYER_PASSWORD = "correctPlayerPassword";
    private static final String INCORRECT_PLAYER_PASSWORD = "incorrectPlayerPassword";

    private static final GameName GAME_NAME = new GameName("gameName");
    private static final String PLAYER_NAME = "anyPlayer";

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerJoinToGameService playerJoinToGameService;

    @Mock
    private GameService gameService;

    @Mock
    private PasswordChecker passwordChecker;

    @Mock
    private PlayerInGameService playerInGameService;

    @Test
    void shouldRegisterPlayerAndSaveItToDatabaseWhenPlayerDoesntExist() {
        // given
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, CORRECT_PLAYER_PASSWORD, CORRECT_GAME_PASSWORD);
        givenGameExists(GAME_NAME);
        givenGamePasswordsMatch();
        givenPlayerDoesntExists(new PlayerName(PLAYER_NAME));
        when(playerJoinToGameService.add(any(), any())).thenReturn(PlayerInGame.from(PlayerInGameCreator.createPlayer(GAME_NAME, new PlayerName(PLAYER_NAME)), true));
        when(passwordChecker.encodePassword(CORRECT_PLAYER_PASSWORD)).thenReturn(CORRECT_PLAYER_PASSWORD);

        // when
        playerService.joinPlayer(GAME_NAME, registerPlayerRequest);

        // then
        verify(playerRepository).save(any());
    }

    @Test
    void shouldThrowPlayerExistsWhenPlayerExistsInDifferentGame() {
        // given
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, CORRECT_PLAYER_PASSWORD, CORRECT_GAME_PASSWORD);
        givenGameExists(GAME_NAME);
        givenGamePasswordsMatch();
        givenPlayerExists(new PlayerName(PLAYER_NAME));
        givenPlayerPasswordsMatch();
        when(playerInGameService.findPlayerInGame(GAME_NAME, new PlayerName(PLAYER_NAME))).thenThrow(PlayerNotFoundInGameException.class);

        // when & then
        assertThrows(PlayerAlreadyExistsException.class, () -> playerService.joinPlayer(GAME_NAME, registerPlayerRequest));
    }

    @Test
    void shouldThrowIncorrectPasswordWhenPlayerExistsButPasswordIsWrong() {
        // given
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, INCORRECT_PLAYER_PASSWORD, CORRECT_GAME_PASSWORD);
        givenGameExists(GAME_NAME);
        givenGamePasswordsMatch();
        givenPlayerExists(new PlayerName(PLAYER_NAME));
        givenPlayerPasswordsDoesntMatch();

        // when & then
        assertThrows(IncorrectPasswordException.class, () -> playerService.joinPlayer(GAME_NAME, registerPlayerRequest));
    }

    @Test
    void shouldThrowGameNotExistsWhenGameWasntCreated() {
        // given
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, CORRECT_PLAYER_PASSWORD, CORRECT_GAME_PASSWORD);
        givenGameNotExists(GAME_NAME);

        // when & then
        assertThrows(GameNotExistsException.class, () -> playerService.joinPlayer(GAME_NAME, registerPlayerRequest));
    }

    @Test
    void shouldThrowIncorrectGamePasswordWhenGamePasswordDidntMatchWithSaved() {
        // given
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, CORRECT_PLAYER_PASSWORD, INCORRECT_GAME_PASSWORD);
        givenGameExists(GAME_NAME);
        givenGamePasswordsDoesntMatch();

        // when & then
        assertThrows(IncorrectPasswordException.class, () -> playerService.joinPlayer(GAME_NAME, registerPlayerRequest));
    }

    private void givenPlayerExists(PlayerName playerName) {
        when(playerRepository.findByName(playerName)).thenReturn(of(PlayerCreator.player(playerName, CORRECT_PLAYER_PASSWORD)));
    }

    private void givenPlayerDoesntExists(PlayerName playerName) {
        when(playerRepository.findByName(playerName)).thenReturn(empty());
    }

    private void givenGamePasswordsMatch() {
        when(passwordChecker.checkPassword(any(GameEntity.class), any())).thenReturn(true);
    }

    private void givenGamePasswordsDoesntMatch() {
        when(passwordChecker.checkPassword(any(GameEntity.class), any())).thenReturn(false);
    }

    private void givenPlayerPasswordsMatch() {
        when(passwordChecker.checkPassword(any(PlayerEntity.class), any())).thenReturn(true);
    }

    private void givenPlayerPasswordsDoesntMatch() {
        when(passwordChecker.checkPassword(any(PlayerEntity.class), any())).thenReturn(false);
    }

    private void givenGameExists(GameName gameName) {
        when(gameService.findGame(gameName)).thenReturn(of(GameCreator.game(gameName, CORRECT_GAME_PASSWORD)));
    }

    private void givenGameNotExists(GameName gameName) {
        when(gameService.findGame(gameName)).thenReturn(empty());
    }
}
