package pl.btsoftware.ship.game.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.events.EventFieldDto;
import pl.btsoftware.ship.game.events.SpecialFieldKind;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionDto;
import pl.btsoftware.ship.registration.exception.GameNotExistsException;
import pl.btsoftware.ship.shared.PlayerName;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.shared.Round;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@WebMvcTest(controllers = BoardRestController.class)
class BoardRestControllerTest {
    private static final String FIRST_PLAYER_NAME = "firstPlayer";
    private static final String SECOND_PLAYER_NAME = "secondPlayer";
    private static final Round ROUND = Round.ROUND_1_MOVE;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardInformationService boardInformationService;

    @Test
    void shouldReturnActualSituationOnBoard() throws Exception {
        // given
        String gameName = "anyName";
        String path = "/game/" + gameName + "/board";
        when(boardInformationService.boardCreator(any())).thenReturn(BoardCreator.create(anySituationOnBoard(), anyEvents(), ROUND));

        // when
        String response = mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        checkRound(response);
        checkPlayers(response);
        checkStartingPoints(response);
        checkEndingPoints(response);
        checkCoordinates(response);
        checkSpecialFields(response);
    }

    @Test
    void shouldReturnNotFoundWhenGameNotExists() throws Exception {
        // given
        String gameName = "anyName";
        String path = "/game/" + gameName + "/board";
        when(boardInformationService.boardCreator(any())).thenThrow(GameNotExistsException.class);

        // when & then
        mockMvc.perform(get(path)).andExpect(status().isNotFound());
    }

    private List<EventFieldDto> anyEvents() {
        List<EventFieldDto> events = new ArrayList<>();
        events.add(new EventFieldDto(new PositionOnBoard(2, 2), SpecialFieldKind.BOTTLE));
        events.add(new EventFieldDto(new PositionOnBoard(4, 2), SpecialFieldKind.ADVENTURE));
        events.add(new EventFieldDto(new PositionOnBoard(3, 4), SpecialFieldKind.TREASURE));

        return events;
    }

    private List<PlayerPositionDto> anySituationOnBoard() {
        List<PlayerPositionDto> playerPositionDtos = new ArrayList<>();
        playerPositionDtos.add(new PlayerPositionDto(new PlayerName(FIRST_PLAYER_NAME), Country.JAMAICA, new PositionOnBoard(1, 1)));
        playerPositionDtos.add(new PlayerPositionDto(new PlayerName(SECOND_PLAYER_NAME), Country.HAITI, new PositionOnBoard(2, 5)));
        playerPositionDtos.add(new PlayerPositionDto(new PlayerName("thirdPlayer"), Country.CUBA));

        return playerPositionDtos;
    }

    private void checkRound(String response) {
        assertThat(readPath(response, "$.round")).isEqualTo(ROUND.toString());
    }

    private void checkPlayers(String response) {
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 1 && @.y == 1)].players[*].name")).contains(FIRST_PLAYER_NAME);
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 1 && @.y == 1)].players[*].country")).contains(Country.JAMAICA.getName());
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 2 && @.y == 5)].players[*].name")).contains(SECOND_PLAYER_NAME);
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 2 && @.y == 5)].players[*].country")).contains(Country.HAITI.getName());
    }

    private void checkSpecialFields(String response) {
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 2 && @.y == 2)].event.kind")).contains("bottle");
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 4 && @.y == 2)].event.kind")).contains("adventure");
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 3 && @.y == 4)].event.kind")).contains("treasure");
    }

    private void checkCoordinates(String response) {
        assertThat(readPath(response, "$.rows[*].fields[*].x")).doesNotContain("0");
        assertThat(readPath(response, "$.rows[*].fields[*].x")).doesNotContain("9");
        assertThat(readPath(response, "$.rows[*].fields[*].y")).doesNotContain("0");
        assertThat(readPath(response, "$.rows[*].fields[*].y")).doesNotContain("10");
    }

    private void checkEndingPoints(String response) {
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 4 && @.y == 9)].is_end_point")).contains("true");
        assertThat(readPath(response, "$.rows[*].fields[?(@.x == 5 && @.y == 9)].is_end_point")).contains("true");
    }

    private void checkStartingPoints(String response) {
        assertThat(readPath(response, "$.rows[*].fields[?(@.y == 1)].is_start_point")).doesNotContain("false");
        assertThat(readPath(response, "$.rows[*].fields[?(@.y == 0)].is_start_point")).doesNotContain("true");
    }
}
