package pl.btsoftware.ship.registration.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.registration.game.exception.GameAlreadyExistsException;

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
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

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
        when(gameService.register(any())).thenReturn(new GameName(gameName));

        // when
        String response = mockMvc.perform(post(path).content(gameRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.name")).isEqualTo(gameName);
        verify(gameService).register(any());
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
        when(gameService.register(any())).thenThrow(GameAlreadyExistsException.class);

        // when & then
        mockMvc.perform(post(path).content(gameRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
