package pl.btsoftware.ship.game.move;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.events.NextActionKind;
import pl.btsoftware.ship.game.events.SpecialFieldKind;
import pl.btsoftware.ship.game.playerInGame.PlayerMoveRestController;
import pl.btsoftware.ship.game.playerInGame.PlayerMoverService;
import pl.btsoftware.ship.game.playerInGame.PlayerNextActionDto;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.player.PlayerName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlayerMoveRestController.class)
class PlayerMoveRestControllerTest {
    private static final String GAME_NAME = "anyName";
    private static final String PLAYER_NAME = "anyPlayerName";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerMoverService playerMoverService;

    @Test
    void shouldReturnNotFoundOnPlayerMoveWhenGameNotExists() throws Exception {
        // given
        String path = "/game/" + GAME_NAME + "/player/" + PLAYER_NAME + "/move";
        int x = 8;
        int y = 5;
        String request = "{\n" +
                "   \"x\":\"" + x + "\",\n" +
                "   \"y\":\"" + y + "\"\n" +
                "}";
        doThrow(GameNotExistsException.class).when(playerMoverService).movePlayer(new GameName(GAME_NAME), new PlayerName(PLAYER_NAME), new PositionOnBoard(x, y));

        // when & then
        mockMvc.perform(post(path).content(request).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundOnPlayerMoveWhenPlayerNotExists() throws Exception {
        // given
        String path = "/game/" + GAME_NAME + "/player/" + PLAYER_NAME + "/move";
        int x = 8;
        int y = 5;
        String request = "{\n" +
                "   \"x\":\"" + x + "\",\n" +
                "   \"y\":\"" + y + "\"\n" +
                "}";
        doThrow(PlayerNotFoundInGameException.class).when(playerMoverService).movePlayer(new GameName(GAME_NAME), new PlayerName(PLAYER_NAME), new PositionOnBoard(x, y));

        // when & then
        mockMvc.perform(post(path)
                        .content(request).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenCoordinatesAreWrong() throws Exception {
        // given
        String path = "/game/" + GAME_NAME + "/player/" + PLAYER_NAME + "/move";
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
        String path = "/game/" + GAME_NAME + "/player/" + PLAYER_NAME + "/move";
        int x = 8;
        int y = 5;
        when(playerMoverService.movePlayer(new GameName(GAME_NAME), new PlayerName(PLAYER_NAME), new PositionOnBoard(x, y))).thenReturn(new PlayerNextActionDto("any", "any", NextActionKind.QUESTION, SpecialFieldKind.BOTTLE));
        String request = "{\n" +
                "   \"x\":\"" + x + "\",\n" +
                "   \"y\":\"" + y + "\"\n" +
                "}";

        // when
        String response = mockMvc.perform(post(path)
                        .content(request).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(JsonPath.<String>read(response, "$.title")).isEqualTo("any");
        assertThat(JsonPath.<String>read(response, "$.description")).isEqualTo("any");
        assertThat(JsonPath.<String>read(response, "$.action")).isEqualTo("question");
        assertThat(JsonPath.<String>read(response, "$.type")).isEqualTo("bottle");
    }
}
