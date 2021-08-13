package pl.btsoftware.ship.game.move;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.playerInGame.PlayerMoverService;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.player.PlayerName;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlayerMoveRestController.class)
class PlayerMoveRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerMoverService playerMoverService;

    @Test
    void shouldReturnNotFoundOnPlayerMoveWhenGameNotExists() throws Exception {
        // given
        String gameName = "anyName";
        String playerName = "anyPlayerName";
        String path = "/game/" + gameName + "/player/" + playerName + "/move";
        int x = 8;
        int y = 5;
        String request = "{\n" +
                "   \"x\":\"" + x + "\",\n" +
                "   \"y\":\"" + y + "\"\n" +
                "}";
        doThrow(GameNotExistsException.class).when(playerMoverService).movePlayer(new GameName(gameName), new PlayerName(playerName), new PositionOnBoard(x, y));

        // when & then
        mockMvc.perform(post(path)
                        .content(request).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundOnPlayerMoveWhenPlayerNotExists() throws Exception {
        // given
        String gameName = "anyName";
        String playerName = "anyPlayerName";
        String path = "/game/" + gameName + "/player/" + playerName + "/move";
        int x = 8;
        int y = 5;
        String request = "{\n" +
                "   \"x\":\"" + x + "\",\n" +
                "   \"y\":\"" + y + "\"\n" +
                "}";
        doThrow(PlayerNotFoundInGameException.class).when(playerMoverService).movePlayer(new GameName(gameName), new PlayerName(playerName), new PositionOnBoard(x, y));

        // when & then
        mockMvc.perform(post(path)
                        .content(request).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenCoordinatesAreWrong() throws Exception {
        // given
        String gameName = "anyName";
        String playerName = "anyPlayerName";
        String path = "/game/" + gameName + "/player/" + playerName + "/move";
        int x = 12;
        int y = 0;
        String request = "{\n" +
                "   \"x\":\"" + x + "\",\n" +
                "   \"y\":\"" + y + "\"\n" +
                "}";

        // when & then
        mockMvc.perform(post(path)
                        .content(request).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAcceptedWhenPlayerMove() throws Exception {
        // given
        String gameName = "anyName";
        String playerName = "anyPlayerName";
        String path = "/game/" + gameName + "/player/" + playerName + "/move";
        int x = 8;
        int y = 5;
        String request = "{\n" +
                "   \"x\":\"" + x + "\",\n" +
                "   \"y\":\"" + y + "\"\n" +
                "}";

        // when & then
        mockMvc.perform(post(path)
                        .content(request).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted());

        // then
        verify(playerMoverService).movePlayer(new GameName(gameName), new PlayerName(playerName), new PositionOnBoard(x, y));
    }
}
