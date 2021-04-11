package pl.btsoftware.ship.game.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@WebMvcTest(controllers = RegisterRestController.class)
class RegisterRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRegistrationService gameRegistrationService;

    @MockBean
    private GameJoinerService gameJoinerService;

    @Test
    void shouldReturnCreatedCodeAndSaveGameToDatabase() throws Exception {
        // given
        String gameName = "any";
        String path = "/game";
        String gameRequest = "{\n" +
                "   \"gameName\":\"" + gameName + "\",\n" +
                "   \"password\":\"any\",\n" +
                "   \"startDate\":\"2020-04-07\"\n" +
                "}";
        when(gameRegistrationService.register(any())).thenReturn(new GameName(gameName));

        // when
        String response = mockMvc.perform(post(path).content(gameRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.name")).isEqualTo(gameName);
        verify(gameRegistrationService).register(any());
    }

    @Test
    void shouldReturnBadRequestResponseWhenProjectAlreadyExists() throws Exception {
        // given
        String path = "/game";
        String gameRequest = "{\n" +
                "   \"gameName\":\"any\",\n" +
                "   \"password\":\"any\",\n" +
                "   \"startDate\":\"2020-04-07\"\n" +
                "}";
        when(gameRegistrationService.register(any())).thenThrow(GameAlreadyExistsException.class);

        // when & then
        mockMvc.perform(post(path).content(gameRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnCreatedCodeAndJoinPlayerToGame() throws Exception {
        // given
        String gameName = "gameName";
        String playerName = "playerName";
        String path = "/game/" + gameName + "/player";
        String joinRequest = "{\n" +
                "   \"playerName\":\"" + playerName + "\",\n" +
                "   \"password\":\"any\",\n" +
                "   \"gamePassword\":\"any\"\n" +
                "}";
        when(gameJoinerService.joinPlayer(any(), any())).thenReturn(new PlayerJoined(new PlayerName(playerName), new GameName(gameName), Country.JAMAICA));

        // when
        String response = mockMvc.perform(post(path).content(joinRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.playerName.name")).isEqualTo(playerName);
        assertThat(readPath(response, "$.gameName.name")).isEqualTo(gameName);
        assertThat(readPath(response, "$.country")).isEqualTo(Country.JAMAICA.toString());
    }

    @Test
    void shouldReturnNotFoundWhenGameNotExists() throws Exception {
        // given
        String gameName = "gameName";
        String playerName = "playerName";
        String path = "/game/" + gameName + "/player";
        String joinRequest = "{\n" +
                "   \"playerName\":\"" + playerName + "\",\n" +
                "   \"password\":\"any\",\n" +
                "   \"gamePassword\":\"any\"\n" +
                "}";
        when(gameJoinerService.joinPlayer(any(), any())).thenThrow(GameNotExistsException.class);

        // when & then
        mockMvc.perform(post(path).content(joinRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenReachMaximumNumberOfPlayers() throws Exception {
        // given
        String gameName = "gameName";
        String playerName = "playerName";
        String path = "/game/" + gameName + "/player";
        String joinRequest = "{\n" +
                "   \"playerName\":\"" + playerName + "\",\n" +
                "   \"password\":\"any\",\n" +
                "   \"gamePassword\":\"any\"\n" +
                "}";
        when(gameJoinerService.joinPlayer(any(), any())).thenThrow(MaximumPlayersInGameException.class);

        // when & then
        mockMvc.perform(post(path).content(joinRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenGamePasswordDidNotMatch() throws Exception {
        // given
        String gameName = "gameName";
        String playerName = "playerName";
        String path = "/game/" + gameName + "/player";
        String joinRequest = "{\n" +
                "   \"playerName\":\"" + playerName + "\",\n" +
                "   \"password\":\"any\",\n" +
                "   \"gamePassword\":\"any\"\n" +
                "}";
        when(gameJoinerService.joinPlayer(any(), any())).thenThrow(IncorrectGamePasswordException.class);

        // when & then
        mockMvc.perform(post(path).content(joinRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
