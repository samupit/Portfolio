package anzsjop_portfolio.portfolio.spotify.authorization_code;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpotifyControllerTest {
	
	@Autowired
	SpotifyController spotifyController;

	@Test
	void contextLoads() {
		assertThat(spotifyController).isNotNull();
	}
}
