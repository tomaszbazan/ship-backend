package pl.btsoftware.ship.registration.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.fixtures.GameFixture;
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

    @Test
    void shouldRegisterPlayerAndSaveItToDatabase() {
        // given
        GameName gameName = new GameName("gameName");
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest("anyPlayer", "anyPassword", CORRECT_GAME_PASSWORD);
        givenGameExists(gameName);
        when(playerInGameService.addPlayerToGame(any(), any())).thenReturn(new PlayerInGame(new PlayerName(registerPlayerRequest.getTeamName()), gameName, Country.JAMAICA));

        // when
        playerService.joinPlayer(gameName, registerPlayerRequest);

        // then
        verify(playerRepository).save(any());
    }


    //    @Test
//    void shouldJoinPlayerToGame() {
//        // given
//        GameName gameName = new GameName("gameName");
//        JoinRequest joinRequest = new JoinRequest("anyPlayer", "anyPassword", "anyGamePassword");
//        when(gameRegistrationService.findGame(gameName)).thenReturn(of(game(gameName)));
//        when(playerInGameRepository.save(any())).thenReturn(new PlayerInGameEntity(new PlayerEntity(joinRequest), game(gameName), Country.JAMAICA));
//
//        // when
//        gameJoinerService.joinPlayer(gameName, joinRequest);
//
//        // then
//        verify(playerInGameRepository).save(any());
//    }
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

        // when & then
        assertThrows(IncorrectPasswordException.class, () -> playerService.joinPlayer(gameName, registerPlayerRequest));
    }

    private void givenGameExists(GameName gameName) {
        when(gameService.findGame(gameName)).thenReturn(of(GameFixture.game(gameName, CORRECT_GAME_PASSWORD)));
    }

    private void givenGameNotExists(GameName gameName) {
        when(gameService.findGame(gameName)).thenReturn(empty());
    }
}
