package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.util.ArrayList;
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
    public void shouldGetTokenById() {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Mockito.when(spotifyServiceMock.getTokenById(1)).thenReturn(token);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        
        Token token1 = spotifyServiceFromContext.getTokenById(1);

        Assertions.assertEquals(token, token1);
        Mockito.verify(spotifyServiceMock).getTokenById(1);
    }

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

    @Test
    public void shouldGetAllTokens() {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");
        Token token1 = new Token("asagq4thq487tafadgah9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(token);
        tokens.add(token1);

        Mockito.when(spotifyServiceMock.getAllTokens()).thenReturn(tokens);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        ArrayList<Token> tokens1 = spotifyServiceFromContext
            .getAllTokens();
        
        ArrayList<Token> defaultList = getDefaultTokenList();
        Assertions.assertTrue(areArrayListsEqual(tokens1, defaultList));

        Mockito.verify(spotifyServiceMock).getAllTokens();
    }

    @Test
    public void shouldFailWhenTokenListsDiffer() {
        ArrayList<Token> tokensWithDifferentTimes = getDefaultTokenListWithDifferentTime();

        Mockito.when(spotifyServiceMock.getAllTokens()).thenReturn(tokensWithDifferentTimes);
        SpotifyService spotifyServiceFromContext = context.getBean(SpotifyService.class);
        ArrayList<Token> tokens1 = spotifyServiceFromContext
            .getAllTokens();
        
        ArrayList<Token> defaultList = getDefaultTokenList();

        Assertions.assertTrue(areArrayListsEqual(tokens1, defaultList));
        Mockito.verify(spotifyServiceMock).getAllTokens();
    }

    public ArrayList<Token> getDefaultTokenList() {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");
        Token token1 = new Token("asagq4thq487tafadgah9rzjkhdfvb8asdg0", "Bearer", 3600, "");
        ArrayList<Token> defaultList = new ArrayList<Token>();
        defaultList.add(token);
        defaultList.add(token1);

        return defaultList;
    }

    public ArrayList<Token> getDefaultTokenListWithDifferentTime() {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 600, "");
        Token token1 = new Token("asagq4thq487tafadgah9rzjkhdfvb8asdg0", "Bearer", 600, "");
        ArrayList<Token> defaultListWithDifferentTime = new ArrayList<Token>();
        defaultListWithDifferentTime.add(token);
        defaultListWithDifferentTime.add(token1);
        
        return defaultListWithDifferentTime;
    }

    public boolean areArrayListsEqual(ArrayList<Token> list1, ArrayList<Token> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getAccessToken() == list2.get(i).getAccessToken() 
                && list1.get(i).getTokenType() == list2.get(i).getTokenType() 
                && list1.get(i).getExpiresIn() == list2.get(i).getExpiresIn()
                && list1.get(i).getScope() == list2.get(i).getScope()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}
