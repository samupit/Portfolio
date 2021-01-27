package anzsjop_portfolio.portfolio.spotify.authorization_code;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc

public class SpotifyServiceTest {
    
    @Autowired
    private SpotifyService spotifyService;

    @Test
    public void shouldGetTokenById() throws Exception {
        Token token = new Token();
        spotifyService.saveToken(token);
        System.out.println(spotifyService.getTokenById(1).toString());
    }

}
