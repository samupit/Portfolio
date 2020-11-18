package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class SpotifyController {

    private static final String client_id = "c251e8621f594d1b9103deede890cbbc";
    private static final String clientSecret = "25d0dd118b3649b68a1b27f3aace65c6";

    @Autowired
    WebClient.Builder getWebClientBuilder;

    @RequestMapping(value="/spotify/token", method=RequestMethod.GET) // this is our application layer
    public @ResponseBody String spotifyAuthorization() {
        String clientCredentials = client_id + ":" + clientSecret;
        //private String encodedClient_id = Base64.getEncoder().encodeToString(client_id.getBytes());
        String encodedClientCredentials = Base64.getEncoder().encodeToString(clientCredentials.getBytes());
        return "Hello World";    
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder()
        .baseUrl("https://accounts.spotify.com");
    }
    
}
    