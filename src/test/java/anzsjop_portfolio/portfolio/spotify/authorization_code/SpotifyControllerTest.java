package anzsjop_portfolio.portfolio.spotify.authorization_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc

public class SpotifyControllerTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    private SpotifyController spotifyControllerMock;
    
    @Test
    public void shouldRequestSpotifyAuthorization() throws Exception {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Mockito.when(spotifyControllerMock.spotifyAuthorization()).thenReturn(token);
        SpotifyController spotifyControllerFromContext = context.getBean(SpotifyController.class);
        
        Token token1 = spotifyControllerFromContext.spotifyAuthorization();

        Assertions.assertEquals(token, token1);
        Mockito.verify(spotifyControllerMock).spotifyAuthorization();
    }

}    
