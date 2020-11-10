package anzsjop_portfolio.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;



@Controller
public class SpotifyController {

    @Autowired
    WebClient.Builder getWebClientBuilder;

    @RequestMapping("/")
    public @ResponseBody String spotifyAuthorization() {
        return getWebClientBuilder.build()
            .method(HttpMethod.GET)
            .uri("https://accounts.spotify.com/authorize")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }


    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

}
    

