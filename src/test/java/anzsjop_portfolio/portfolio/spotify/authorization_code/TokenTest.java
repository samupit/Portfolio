package anzsjop_portfolio.portfolio.spotify.authorization_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

public class TokenTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    private Token tokenMock;

    Token token = new Token();

    @Test
    public void shouldSetAndGetAccessToken() throws Exception {
        token.setAccessToken("fdsgahdfshsgjsh436514362547");

        token.getAccessToken();
        Assertions.assertEquals("fdsgahdfshsgjsh436514362547", token.getAccessToken());
    }

    @Test
    public void shouldSetAndGetTokenType() throws Exception {
        token.setTokenType("Bearer");

        token.getTokenType();
        Assertions.assertEquals("Bearer", token.getTokenType());
    }

    @Test
    public void shouldSetAndGetExpiresIn() throws Exception {
        token.setExpiresIn(35345);

        token.getExpiresIn();
        Assertions.assertEquals(35345, token.getExpiresIn());
    }

}
