package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Test
    public void shouldChangeJsonStringToMap() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = "{\"access_token\":\"BQDlPBW4yeqDKbfLMGb7jrEAIj9gc770\",\"token_type\":\"Bearer\",\"expires_in\":3600,\"scope\":\"\"}";
        HashMap<String, Object> accessTokenHashMap = new HashMap<String, Object>();
        try {
            accessTokenHashMap = mapper.readValue(jsonResponse, new TypeReference<HashMap<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        Mockito.when(spotifyServiceMock.jsonStringToMap(jsonResponse)).thenReturn(accessTokenHashMap);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        HashMap<String, Object> accessTokenHashMap1 = spotifyServiceFromContext
            .jsonStringToMap(jsonResponse);

        Assertions.assertEquals(accessTokenHashMap, accessTokenHashMap1);
        Mockito.verify(spotifyServiceMock).jsonStringToMap(jsonResponse);
    }

    @Test
    public void shouldGetAllTokens() throws Exception {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Mockito.when(spotifyServiceMock.requestAndSaveAccessToken()).thenReturn(token);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        Token token1 = spotifyServiceFromContext.requestAndSaveAccessToken();

        Assertions.assertEquals(token, token1);
        Mockito.verify(spotifyServiceMock).requestAndSaveAccessToken();
    }

}
