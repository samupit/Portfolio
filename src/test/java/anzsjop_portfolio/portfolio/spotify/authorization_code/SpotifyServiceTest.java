package anzsjop_portfolio.portfolio.spotify.authorization_code;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

public class SpotifyServiceTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpotifyService spotifyService;

    @Disabled
    @Test
    public void shouldReceiveToken() throws Exception {
        this.mockMvc.perform(get("/spotify/token"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("asd")));
    }

}
