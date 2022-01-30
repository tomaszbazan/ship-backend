package pl.btsoftware.ship.registration.player;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.creators.GoodsCreator;
import pl.btsoftware.ship.game.board.PlayerSituation;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.playerInGame.PlayerInGame;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.player.exception.IncorrectPasswordException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@WebMvcTest(controllers = RegisterPlayerRestController.class)
class RegisterPlayerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void shouldReturnCreatedCodeAndJoinPlayerToGame() throws Exception {
        // given
        String gameName = "gameName";
        String playerName = "playerName";
        String path = "/game/" + gameName + "/player";
        String joinRequest = "{\n" +
                "   \"playerName\":\"" + playerName + "\",\n" +
                "   \"playerPassword\":\"any\",\n" +
                "   \"gamePassword\":\"any\"\n" +
                "}";
        when(playerService.joinPlayer(any(), any())).thenReturn(new PlayerInGame(new PlayerName(playerName), new GameName(gameName), Country.JAMAICA, GoodsCreator.jamaicaStartGoods(), true));

        // when
        String response = mockMvc.perform(post(path).content(joinRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.player_name.name")).isEqualTo(playerName);
        assertThat(readPath(response, "$.game_name.name")).isEqualTo(gameName);
        assertThat(readPath(response, "$.country")).isEqualTo(Country.JAMAICA.getName());
        assertThat(readPath(response, "$.goods[0].type")).isEqualTo("Citrus");
        assertThat(readPath(response, "$.goods[0].amount")).isEqualTo("10");
    }

    @Test
    void shouldReturnNotFoundWhenGameNotExists() throws Exception {
        // given
        String gameName = "gameName";
        String playerName = "playerName";
        String path = "/game/" + gameName + "/player";
        String joinRequest = "{\n" +
                "   \"playerName\":\"" + playerName + "\",\n" +
                "   \"playerPassword\":\"any\",\n" +
                "   \"gamePassword\":\"any\"\n" +
                "}";
        when(playerService.joinPlayer(any(), any())).thenThrow(GameNotExistsException.class);

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
                "   \"playerPassword\":\"any\",\n" +
                "   \"gamePassword\":\"any\"\n" +
                "}";
        when(playerService.joinPlayer(any(), any())).thenThrow(PlayerSituation.MaximumPlayersInGameException.class);

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
                "   \"playerPassword\":\"any\",\n" +
                "   \"gamePassword\":\"any\"\n" +
                "}";
        when(playerService.joinPlayer(any(), any())).thenThrow(IncorrectPasswordException.class);

        // when & then
        mockMvc.perform(post(path).content(joinRequest).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
