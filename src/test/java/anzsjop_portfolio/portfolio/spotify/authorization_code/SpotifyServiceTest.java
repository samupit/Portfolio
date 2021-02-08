package anzsjop_portfolio.portfolio.spotify.authorization_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SpotifyServiceTest {
    
    @Autowired
    ApplicationContext context;

    @MockBean
    private SpotifyService spotifyServiceMock;
    
    @Test
    public void shouldGetTokenById() throws Exception {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Mockito.when(spotifyServiceMock.getTokenById(1)).thenReturn(token);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        Token token1 = spotifyServiceFromContext.getTokenById(1);

        Assertions.assertEquals(token, token1);
        Mockito.verify(spotifyServiceMock).getTokenById(1);
    }

    @Test
    public void shouldGetNewestToken() throws Exception {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Mockito.when(spotifyServiceMock.getNewestToken()).thenReturn(token);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        Token token1 = spotifyServiceFromContext.getNewestToken();

        Assertions.assertEquals(token, token1);
        Mockito.verify(spotifyServiceMock).getNewestToken();
    }

    @Test
    public void shouldRequestAndSaveAccessToken() throws Exception {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Mockito.when(spotifyServiceMock.requestAndSaveAccessToken()).thenReturn(token);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        Token token1 = spotifyServiceFromContext.requestAndSaveAccessToken();

        Assertions.assertEquals(token, token1);
        Mockito.verify(spotifyServiceMock).requestAndSaveAccessToken();
    }

}
