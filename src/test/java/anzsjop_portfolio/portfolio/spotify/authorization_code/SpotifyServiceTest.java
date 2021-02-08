package anzsjop_portfolio.portfolio.spotify.authorization_code;

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
    private SpotifyService spotifyServiceMock;

    Token token = new Token();

    @Test
    public void shouldGetTokenById() throws Exception {
        spotifyServiceMock.saveToken(token);
        System.out.println(spotifyServiceMock.getTokenById(1).toString());
    }

}
