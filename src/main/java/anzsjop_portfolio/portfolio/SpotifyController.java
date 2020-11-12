package anzsjop_portfolio.portfolio;


import java.nio.charset.Charset;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;




@Controller
public class SpotifyController {

    private static final String client_id = "c251e8621f594d1b9103deede890cbbc";
    // private static final String clientSecret = "25d0dd118b3649b68a1b27f3aace65c6";
    private static final String redirect_uri = "https://example.com/callback";

    @Autowired
    WebClient.Builder getWebClientBuilder;

    @RequestMapping("/")
    public @ResponseBody String spotifyAuthorization() {
        return getWebClientBuilder.build()
            .method(HttpMethod.HEAD)
            .uri("https://accounts.spotify.com/authorize?client_id=" 
            + client_id + "&response_type=code&redirect_uri=" 
            + redirect_uri +"&scope=user-read-private%20user-read-email&state=34fFs29kd09") 
            .accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML)
            .acceptCharset(Charset.forName("UTF-8"))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder()
        .baseUrl("http://localhost:8080")
	    .defaultCookie("cookieKey", "cookieValue")
	    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
	    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"));
    }
    
}
