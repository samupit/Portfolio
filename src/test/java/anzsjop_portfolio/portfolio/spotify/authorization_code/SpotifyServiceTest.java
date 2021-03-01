package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.util.HashMap;
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
    public void shouldGetNewestToken() {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Mockito.when(spotifyServiceMock.getNewestToken()).thenReturn(token);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        Token token1 = spotifyServiceFromContext.getNewestToken();

        Assertions.assertEquals(token, token1);
        Mockito.verify(spotifyServiceMock).getNewestToken();
    }

    @Test
    public void shouldGetNullWhenNoTokensExist() {
        Mockito.when(spotifyServiceMock.getNewestToken()).thenReturn(null);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        Token token1 = spotifyServiceFromContext.getNewestToken();

        Assertions.assertEquals(null, token1);
        Mockito.verify(spotifyServiceMock).getNewestToken();
    }

    @Test
    public void shouldRequestAndSaveAccessToken() {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Mockito.when(spotifyServiceMock.requestAndSaveAccessToken()).thenReturn(token);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        Token token1 = spotifyServiceFromContext.requestAndSaveAccessToken();

        Assertions.assertEquals(token, token1);
        Mockito.verify(spotifyServiceMock).requestAndSaveAccessToken();
    }

    @Test
    public void shouldFailWhenMapsDiffer() {
        String jsonResponse = "";
        HashMap<String, Object> accessTokenHashMapWithDifferentTime = defaultAccessTokenHashMapWithDifferentTime();

        Mockito.when(spotifyServiceMock.jsonStringToMap(jsonResponse)).thenReturn(accessTokenHashMapWithDifferentTime);

        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        HashMap<String, Object> accessTokenHashMap1 = spotifyServiceFromContext
            .jsonStringToMap(jsonResponse);
        
        HashMap<String, Object> defaultMap = defaultAccessTokenHashMap();
        Assertions.assertEquals(accessTokenHashMap1, defaultMap);
        Mockito.verify(spotifyServiceMock).jsonStringToMap(jsonResponse);
    }

    @Test
    public void shouldChangeJsonStringToMap() {
        HashMap<String, Object> accessTokenHashMap = new HashMap<>();
        accessTokenHashMap.put("access_token", "BQDlPBW4yeqDKbfLMGb7jrEAIj9gc770");
        accessTokenHashMap.put("token_type", "Bearer");
        accessTokenHashMap.put("expires_in", "3600");
        accessTokenHashMap.put("scope", "");
        String jsonResponse = "";

        Mockito.when(spotifyServiceMock.jsonStringToMap(jsonResponse)).thenReturn(accessTokenHashMap);

        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        HashMap<String, Object> accessTokenHashMap1 = spotifyServiceFromContext
            .jsonStringToMap(jsonResponse);
        
        HashMap<String, Object> defaultMap = defaultAccessTokenHashMap();
        Assertions.assertEquals(accessTokenHashMap1, defaultMap);
        Mockito.verify(spotifyServiceMock).jsonStringToMap(jsonResponse);
    }

    public HashMap<String, Object> defaultAccessTokenHashMap() {
        HashMap<String, Object> accessTokenHashMap = new HashMap<>();
        accessTokenHashMap.put("access_token", "BQDlPBW4yeqDKbfLMGb7jrEAIj9gc770");
        accessTokenHashMap.put("token_type", "Bearer");
        accessTokenHashMap.put("expires_in", "3600");
        accessTokenHashMap.put("scope", "");

        return accessTokenHashMap;
    }

    public HashMap<String, Object> defaultAccessTokenHashMapWithDifferentTime() {
        HashMap<String, Object> accessTokenHashMap = new HashMap<>();
        accessTokenHashMap.put("access_token", "BQDlPBW4yeqDKbfLMGb7jrEAIj9gc770");
        accessTokenHashMap.put("token_type", "Bearer");
        accessTokenHashMap.put("expires_in", "600");
        accessTokenHashMap.put("scope", "");

        return accessTokenHashMap;
    }

}
