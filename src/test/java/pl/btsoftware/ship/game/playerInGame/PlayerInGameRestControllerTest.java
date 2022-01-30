package pl.btsoftware.ship.game.playerInGame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.creators.GoodsCreator;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@WebMvcTest(controllers = PlayerInGameRestController.class)
class PlayerInGameRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerInGameService playerInGameService;

    @Test
    void shouldReturnInformationAboutPlayerInGame() throws Exception {
        // given
        GameName gameName = new GameName("anyName");
        PlayerName playerName = new PlayerName("anyName");
        String path = "/game/" + gameName.getName() + "/player/" + playerName.getName();
        when(playerInGameService.findPlayerInGame(gameName, playerName)).thenReturn(anyInformationAboutPlayer(gameName, playerName));

        // when
        String response = mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.player_name.name")).isEqualTo(playerName.getName());
        assertThat(readPath(response, "$.game_name.name")).isEqualTo(gameName.getName());
        assertThat(readPath(response, "$.country")).isEqualTo("Jamaica");
        assertThat(readPath(response, "$.goods")).isNotNull();
    }

    private PlayerInGame anyInformationAboutPlayer(GameName gameName, PlayerName playerName) {
        return new PlayerInGame(playerName, gameName, Country.JAMAICA, GoodsCreator.jamaicaStartGoods(), true);
    }
}
