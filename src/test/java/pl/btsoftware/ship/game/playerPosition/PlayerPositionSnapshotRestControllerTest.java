package pl.btsoftware.ship.game.playerPosition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@WebMvcTest(controllers = PlayerSituationRestController.class)
class PlayerPositionSnapshotRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerPositionService playerPositionService;

    @Test
    void shouldReturnInformationAboutPlayerInGame() throws Exception {
        // given
        GameName gameName = new GameName("anyName");
        PlayerName playerName = new PlayerName("anyName");
        String path = "/game/" + gameName.value() + "/player/" + playerName.value();
        when(playerPositionService.get(any(), any())).thenReturn(PlayerPositionFixture.createPlayer(gameName, playerName, true));

        // when
        String response = mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.player.name")).isEqualTo(playerName.value());
        assertThat(readPath(response, "$.game.name")).isEqualTo(gameName.value());
        assertThat(readPath(response, "$.first_login")).isEqualTo("true");
        assertThat(readPath(response, "$.country")).isEqualTo("Jamaica");
        assertThat(readPath(response, "$.goods")).isNotNull();
        assertThat(readPath(response, "$.cards")).isNotNull();
    }
}
