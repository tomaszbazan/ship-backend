package pl.btsoftware.ship.game.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.btsoftware.ship.JsonMapper.readPath;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BoardRestController.class)
class BoardRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
        checkCordinates(response);
        checkSpecialFields(response);
    }

    private void checkSpecialFields(String response) {
        assertThat(readPath(response, "$.fields[?(@.x == 2 && @.y == 2)].special.kind")).contains("bottle");
        assertThat(readPath(response, "$.fields[?(@.x == 2 && @.y == 2)].special.number")).contains("1");
        assertThat(readPath(response, "$.fields[?(@.x == 4 && @.y == 2)].special.kind")).contains("adventure");
        assertThat(readPath(response, "$.fields[?(@.x == 4 && @.y == 2)].special.number")).contains("3");
        assertThat(readPath(response, "$.fields[?(@.x == 3 && @.y == 4)].special.kind")).contains("treasure");
        assertThat(readPath(response, "$.fields[?(@.x == 3 && @.y == 4)].special.number")).contains("36");
    }

    private void checkCordinates(String response) {
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
