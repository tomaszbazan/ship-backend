package pl.btsoftware.ship.game.logic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.game.registration.Country;
import pl.btsoftware.ship.game.registration.GameName;
import pl.btsoftware.ship.game.registration.PositionOnBoard;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@WebMvcTest(controllers = BoardRestController.class)
class BoardRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardInformationService boardInformationService;

    @Test
    void shouldReturnDefaultBoard() throws Exception {
        // given
        String path = "/game";

        // when
        String response = mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        checkStartingPoints(response);
        checkEndingPoints(response);
        checkCoordinates(response);
        checkSpecialFields(response);
    }

    @Test
    void shouldReturnActualSituationOnBoard() throws Exception {
        // given
        String gameName = "anyName";
        String path = "/game/" + gameName;
        when(boardInformationService.actualSituation(new GameName(gameName))).thenReturn(anySituationOnBoard());

        // when
        String response = mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        assertThat(readPath(response, "$.players[*].player_name")).isNotEmpty();
        assertThat(readPath(response, "$.players[*].country")).isNotEmpty();
        assertThat(readPath(response, "$.players[*].coordinates.x")).isNotEmpty();
        assertThat(readPath(response, "$.players[*].coordinates.y")).isNotEmpty();
    }

    private ActualBoardSituation anySituationOnBoard() {
        ActualBoardSituation actualBoardSituation = new ActualBoardSituation();

        List<PlayerPosition> playerPositions = new ArrayList<>();
        playerPositions.add(PlayerPosition.builder().playerName("firstPlayer").country(Country.JAMAICA).coordinates(new PositionOnBoard(1, 1)).build());
        playerPositions.add(PlayerPosition.builder().playerName("secondPlayer").country(Country.HAITI).coordinates(new PositionOnBoard(2, 5)).build());

        actualBoardSituation.setPlayers(playerPositions);

        return actualBoardSituation;
    }

    private void checkSpecialFields(String response) {
        assertThat(readPath(response, "$.fields[?(@.x == 2 && @.y == 2)].special.kind")).contains("bottle");
        assertThat(readPath(response, "$.fields[?(@.x == 2 && @.y == 2)].special.number")).contains("1");
        assertThat(readPath(response, "$.fields[?(@.x == 4 && @.y == 2)].special.kind")).contains("adventure");
        assertThat(readPath(response, "$.fields[?(@.x == 4 && @.y == 2)].special.number")).contains("3");
        assertThat(readPath(response, "$.fields[?(@.x == 3 && @.y == 4)].special.kind")).contains("treasure");
        assertThat(readPath(response, "$.fields[?(@.x == 3 && @.y == 4)].special.number")).contains("36");
    }

    private void checkCoordinates(String response) {
        assertThat(readPath(response, "$.fields[*].x")).doesNotContain("0");
        assertThat(readPath(response, "$.fields[*].x")).doesNotContain("9");
        assertThat(readPath(response, "$.fields[*].y")).doesNotContain("0");
        assertThat(readPath(response, "$.fields[*].y")).doesNotContain("10");
    }

    private void checkEndingPoints(String response) {
        assertThat(readPath(response, "$.fields[?(@.x == 4 && @.y == 9)].is_end_point")).contains("true");
        assertThat(readPath(response, "$.fields[?(@.x == 5 && @.y == 9)].is_end_point")).contains("true");
    }

    private void checkStartingPoints(String response) {
        assertThat(readPath(response, "$.fields[?(@.y == 1)].is_start_point")).doesNotContain("false");
    }
}
