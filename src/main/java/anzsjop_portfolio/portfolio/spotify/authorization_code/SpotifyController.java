package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class SpotifyController {

    @Autowired
    SpotifyService spotifyService;

    @Autowired
    WebClient.Builder getWebClientBuilder;

    @RequestMapping(value = "/spotify/token", method = RequestMethod.GET) // this is our application layer
    public @ResponseBody Token spotifyAuthorization() {
        Token accessToken = new Token();
        if (spotifyService.getAllTokens().isEmpty()) {
            accessToken = spotifyService.requestAndSaveAccessToken();
        } else if (spotifyService.getNewestToken()
            .getExpirationTime()
            .compareTo(LocalDateTime.now()) <= 0) {
            accessToken = spotifyService.requestAndSaveAccessToken();
        } else {
            accessToken = spotifyService.getNewestToken();
        }
        
        return accessToken;
    }

}
