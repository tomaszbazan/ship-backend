package pl.btsoftware.ship.game.playerPosition;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.game.events.EventSnapshot;
import pl.btsoftware.ship.game.events.NextActionKind;
import pl.btsoftware.ship.game.events.SpecialFieldKind;
import pl.btsoftware.ship.game.playerPosition.exception.PlayerNotFoundInGameException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlayerMoveRestController.class)
class PlayerMoveRestControllerTest {
    private static final String GAME_NAME = "anyName";
    private static final String PLAYER_NAME = "anyPlayerName";
    private static final String MOVE_URL = "/game/" + GAME_NAME + "/player/" + PLAYER_NAME + "/move";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerMoveService playerMoveService;

    @Test
    void shouldReturnNotFoundOnPlayerMoveWhenGameNotExists() throws Exception {
        // given
        int x = 8;
        int y = 5;
        String request = """
                {
                   "x2":"%s",
                   "y":"%s"
                }
                """;
        doThrow(PlayerNotFoundInGameException.class).when(playerMoveService).movePlayer(any(), any(), any());

        // when & then
        mockMvc.perform(post(MOVE_URL).content(request.formatted(x, y)).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenCoordinatesAreWrong() throws Exception {
        // given
        int x = 12;
        int y = 0;
        String request = """
                {
                   "x":"%s",
                   "y":"%s"
                }
                """;

        // when & then
        mockMvc.perform(post(MOVE_URL).content(request.formatted(x, y)).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAcceptedWhenPlayerMove() throws Exception {
        // given
        int x = 8;
        int y = 5;
        String request = """
                {
                   "x":"%s",
                   "y":"%s"
                }
                """;
        when(playerMoveService.movePlayer(any(), any(), any())).thenReturn(Optional.of(new EventSnapshot.EventDescription("any", "any", NextActionKind.QUESTION, SpecialFieldKind.BOTTLE)));

        // when
        String response = mockMvc.perform(post(MOVE_URL)
                        .content(request.formatted(x, y)).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(JsonPath.<String>read(response, "$.title")).isEqualTo("any");
        assertThat(JsonPath.<String>read(response, "$.description")).isEqualTo("any");
        assertThat(JsonPath.<String>read(response, "$.action")).isEqualTo("question");
        assertThat(JsonPath.<String>read(response, "$.type")).isEqualTo("bottle");
    }
}
