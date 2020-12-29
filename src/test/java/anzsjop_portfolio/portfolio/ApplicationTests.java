package anzsjop_portfolio.portfolio;

import static org.assertj.core.api.Assertions.assertThat;

import anzsjop_portfolio.portfolio.spotify.authorization_code.SpotifyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc

public class ApplicationTests {

    @Autowired
    private SpotifyController spotifyController;

    @Test
    public void shouldDisplayLoginPage() throws Exception {
        assertThat(spotifyController).isNotNull();
    }

}
