package anzsjop_portfolio.portfolio.spotify.authorization_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TokenTest {

    @Test
    public void shouldGetTokenById() throws Exception {
        Token token = new Token();

        Token token1 = Mockito.spy(token);

        Mockito.doReturn(true).when(token1).getAccessToken(token1.getId());

        Assertions.assertEquals(true, token1.getAccessToken(token1.getId()));
    }

}
