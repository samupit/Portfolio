package anzsjop_portfolio.portfolio.spotify.authorization_code;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

public class SpotifyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpotifyController spotifyController;

    private String token = "{\"access_token\":\"BQDlPBW4yeqDKbfLMGb7jrEAIj9gc770\",\"token_type\":\"Bearer\",\"expires_in\":3600,\"scope\":\"\"}";
    
    
    @Test
    public void shouldReceiveToken() throws Exception {
        when(spotifyController.spotifyAuthorization());
        this.mockMvc.perform(get("/spotify/token"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(token)));
    }
}
