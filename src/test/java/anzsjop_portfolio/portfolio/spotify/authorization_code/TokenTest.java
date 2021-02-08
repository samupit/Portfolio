package anzsjop_portfolio.portfolio.spotify.authorization_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TokenTest {

    Token token = new Token();

    @Test
    public void shouldGetTokenById() throws Exception {
        Token token = new Token("asagq4thq487ta9rzjkhdfvb8asdg0", "Bearer", 3600, "");

        Token token1 = Mockito.spy(token);

        Mockito.doReturn(token).when(token1);

        Assertions.assertEquals(token, token1);
    }

    @Test
    public void shouldSetAndGetAccessToken() throws Exception {
        token.setAccessToken("fdsgahdfshsgjsh436514362547");

        token.getAccessToken();
        Assertions.assertEquals("fdsgahdfshsgjsh436514362547", token.getAccessToken());
    }

}
