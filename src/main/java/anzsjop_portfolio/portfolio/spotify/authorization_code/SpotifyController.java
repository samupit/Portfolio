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
    private static final String redirect_uri = "https://example.com/callback";

    @Autowired
    WebClient.Builder getWebClientBuilder;

   /* @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String logInButton() {
        return "<html>\n" + "<header><title>Welcome to the login page </title></header>\n" + "<body>\n" + "Log in\n"
                + "</body>\n" + "</html>";

    }*/

    @RequestMapping("/api/token")
    public @ResponseBody String spotifyAuthorization() {
        return spotifyCommand;    
    }

        
    private static final String clientCredentials = "c251e8621f594d1b9103deede890cbbc:25d0dd118b3649b68a1b27f3aace65c6";
    //private String encodedClient_id = Base64.getEncoder().encodeToString(client_id.getBytes());
    private String encodedClientCredentials = Base64.getEncoder().encodeToString(clientCredentials.getBytes());

    
    public String spotifyCommand = "curl -H Content-Type: application/x-www-form-urlencoded" +
    " -X POST https://accounts.spotify.com/api/token -d grant_type=client_credentials" + 
    " -H Authorization: Basic " + encodedClientCredentials;

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder()
        .baseUrl("https://accounts.spotify.com");
    }
    
}
    

