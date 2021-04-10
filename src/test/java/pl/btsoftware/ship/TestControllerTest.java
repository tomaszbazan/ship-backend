package pl.btsoftware.ship;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldResponseHelloOnRootPath() throws Exception {
        // given
        String path = "/";

        // when
        String response = mockMvc.perform(get(path)).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        String gameId = JsonPath.read(response, "$");
        assertThat(gameId).isEqualTo("Hello");
    }
}
