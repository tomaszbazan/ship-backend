package pl.btsoftware.ship.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.playerPosition.PlayerJoinService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionFixture;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionService;
import pl.btsoftware.ship.game.playerPosition.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.game.state.GameStateService;
import pl.btsoftware.ship.registration.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.exception.GameRegistrationClosedException;
import pl.btsoftware.ship.registration.exception.IncorrectPasswordException;
import pl.btsoftware.ship.registration.exception.PlayerAlreadyExistsException;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.btsoftware.ship.registration.RegisterPlayerRestController.RegisterPlayerRequest;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    private static final String CORRECT_GAME_PASSWORD = "correctGamePassword";
    private static final String INCORRECT_GAME_PASSWORD = "incorrectGamePassword";
    private static final String CORRECT_PLAYER_PASSWORD = "correctPlayerPassword";
    private static final String INCORRECT_PLAYER_PASSWORD = "incorrectPlayerPassword";

    private static final GameName GAME_NAME = new GameName("gameName");
    private static final PlayerName PLAYER_NAME = new PlayerName("anyPlayer");

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerJoinService playerJoinService;

    @Mock
    private GameService gameService;

    @Mock
    private PasswordChecker passwordChecker;

    @Mock
    private PlayerPositionService playerPositionService;

    @Mock
    private GameStateService gameStateService;

    @Test
    void shouldRegisterPlayerAndSaveItToDatabaseWhenPlayerDoesntExist() {
        // given
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, CORRECT_PLAYER_PASSWORD, CORRECT_GAME_PASSWORD);
        givenGameExists(GAME_NAME);
        givenGamePasswordsMatch();
        givenGameRegistrationIsOpen(GAME_NAME);
        givenPlayerDoesntExists(PLAYER_NAME);
        when(playerJoinService.add(any(), any())).thenReturn(PlayerPositionFixture.createPlayer(GAME_NAME, PLAYER_NAME, true));
        when(passwordChecker.encodePassword(CORRECT_PLAYER_PASSWORD)).thenReturn(CORRECT_PLAYER_PASSWORD);
        when(playerRepository.save(any())).thenReturn(PlayerCreator.player(PLAYER_NAME, CORRECT_PLAYER_PASSWORD));

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
        givenPlayerExists(PLAYER_NAME);
        givenPlayerPasswordsMatch();
        when(playerPositionService.get(GAME_NAME, registerPlayerRequest.player())).thenThrow(PlayerNotFoundInGameException.class);

        // when & then
        assertThrows(PlayerAlreadyExistsException.class, () -> playerService.joinPlayer(GAME_NAME, registerPlayerRequest));
    }

    @Test
    void shouldThrowIncorrectPasswordWhenPlayerExistsButPasswordIsWrong() {
        // given
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, INCORRECT_PLAYER_PASSWORD, CORRECT_GAME_PASSWORD);
        givenGameExists(GAME_NAME);
        givenGamePasswordsMatch();
        givenPlayerExists(PLAYER_NAME);
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

    @Test
    void shouldThrowExceptionWhenRegistrationIsClosed() {
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, CORRECT_PLAYER_PASSWORD, CORRECT_GAME_PASSWORD);
        givenGameExists(GAME_NAME);
        givenGamePasswordsMatch();
        givenGameRegistrationIsClosed(GAME_NAME);

        // when & then
        assertThrows(GameRegistrationClosedException.class, () -> playerService.joinPlayer(GAME_NAME, registerPlayerRequest));
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

    private void givenGameRegistrationIsClosed(GameName gameName) {
        when(gameStateService.canJoinToGame(gameName)).thenReturn(false);
    }

    private void givenGameRegistrationIsOpen(GameName gameName) {
        when(gameStateService.canJoinToGame(gameName)).thenReturn(true);
    }
}
