package pl.btsoftware.ship.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.creators.CardsCreator;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.goods.GoodsCreator;
import pl.btsoftware.ship.game.playerPosition.ActionType;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionSnapshot;
import pl.btsoftware.ship.game.playerPosition.exception.MaximumPlayersInGameException;
import pl.btsoftware.ship.registration.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.exception.IncorrectPasswordException;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@WebMvcTest(controllers = RegisterPlayerRestController.class)
class RegisterPlayerRestControllerTest {

    public static final String GAME_NAME = "gameName";
    public static final String PLAYER_NAME = "playerName";
    public static final String REGISTER_PLAYER_URL = "/game/" + GAME_NAME + "/player";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void shouldReturnCreatedCodeAndJoinPlayerToGame() throws Exception {
        // given
        String joinRequest = """
                {
                   "player":"%s",
                   "playerPassword":"any",
                   "gamePassword":"any"
                }
                """;
        when(playerService.joinPlayer(any(), any())).thenReturn(new PlayerPositionSnapshot(new PlayerName(PLAYER_NAME), new GameName(GAME_NAME), Country.JAMAICA, null, ActionType.JOIN, GoodsCreator.jamaicaStartGoods(), CardsCreator.empty(), true));

        // when
        String response = mockMvc.perform(post(REGISTER_PLAYER_URL).content(joinRequest.formatted(PLAYER_NAME)).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.player.name")).isEqualTo(PLAYER_NAME);
        assertThat(readPath(response, "$.game.name")).isEqualTo(GAME_NAME);
        assertThat(readPath(response, "$.country")).isEqualTo(Country.JAMAICA.getName());
        assertThat(readPath(response, "$.goods[0].type")).isEqualTo("Citrus");
        assertThat(readPath(response, "$.goods[0].amount")).isEqualTo("10");
    }

    @Test
    void shouldReturnNotFoundWhenGameNotExists() throws Exception {
        // given
        String joinRequest = """
                {
                   "player":"%s",
                   "playerPassword":"any",
                   "gamePassword":"any"
                }
                """;
        when(playerService.joinPlayer(any(), any())).thenThrow(GameNotExistsException.class);

        // when & then
        mockMvc.perform(post(REGISTER_PLAYER_URL).content(joinRequest.formatted(PLAYER_NAME)).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenReachMaximumNumberOfPlayers() throws Exception {
        // given
        String joinRequest = """
                {
                   "player":"%s",
                   "playerPassword":"any",
                   "gamePassword":"any"
                }
                """;
        when(playerService.joinPlayer(any(), any())).thenThrow(MaximumPlayersInGameException.class);

        // when & then
        mockMvc.perform(post(REGISTER_PLAYER_URL).content(joinRequest.formatted(PLAYER_NAME)).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenGamePasswordDidNotMatch() throws Exception {
        // given
        String joinRequest = """
                {
                   "player":"%s",
                   "playerPassword":"any",
                   "gamePassword":"any"
                }
                """;
        when(playerService.joinPlayer(any(), any())).thenThrow(IncorrectPasswordException.class);

        // when & then
        mockMvc.perform(post(REGISTER_PLAYER_URL).content(joinRequest.formatted(PLAYER_NAME)).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
