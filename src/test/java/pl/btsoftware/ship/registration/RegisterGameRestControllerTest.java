package pl.btsoftware.ship.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.registration.exception.GameAlreadyExistsException;
import pl.btsoftware.ship.shared.GameName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@WebMvcTest(controllers = RegisterGameRestController.class)
class RegisterGameRestControllerTest {
    public static final String REGISTER_GAME_URL = "/game";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    void shouldReturnCreatedCodeAndSaveGameToDatabase() throws Exception {
        // given
        String gameName = "any";
        String gameRequest = """
                {
                   "game":"%s",
                   "password":"any",
                   "roundTime":"10"
                }
                """;
        when(gameService.register(any())).thenReturn(new GameName(gameName));

        // when
        String response = mockMvc.perform(post(REGISTER_GAME_URL).content(gameRequest.formatted(gameName)).contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.name")).isEqualTo(gameName);
        verify(gameService).register(any());
    }

    @Test
    void shouldReturnBadRequestResponseWhenProjectAlreadyExists() throws Exception {
        // given
        String gameRequest = """
                {
                   "game":"any",
                   "password":"any",
                   "roundTime":"10"
                }""";
        when(gameService.register(any())).thenThrow(GameAlreadyExistsException.class);

        // when & then
        mockMvc.perform(post(REGISTER_GAME_URL).content(gameRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestResponseWhenAnyOfFieldsInRequestAreEmpty() throws Exception {
        // given
        String gameRequest = """
                {
                   "game":"",
                   "password":"",
                   "roundTime":"10"
                }""";

        // when & then
        mockMvc.perform(post(REGISTER_GAME_URL).content(gameRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
