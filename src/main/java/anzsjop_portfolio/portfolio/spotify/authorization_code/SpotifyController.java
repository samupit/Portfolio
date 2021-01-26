package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class SpotifyController {

    private static final Logger logger = LogManager.getLogger(SpotifyController.class);

    private static final String client_id = "c251e8621f594d1b9103deede890cbbc";
    private static final String clientSecret = "25d0dd118b3649b68a1b27f3aace65c6";

    @Autowired
    SpotifyService spotifyService;

    @Autowired
    WebClient.Builder getWebClientBuilder;

    @RequestMapping(value = "/spotify/token", method = RequestMethod.GET) // this is our application layer
    public @ResponseBody Token spotifyAuthorization() {
        Token accessToken = new Token();
        if (spotifyService.getAllTokens().isEmpty()) {
            accessToken = requestAndSaveAccessToken();
        }

        for (int i = 1; i <= spotifyService.getAllTokens().size() ; i++) {
            if (spotifyService.getTokenById(i).getExpirationTime(i).compareTo(LocalDateTime.now()) < 0){
                accessToken = requestAndSaveAccessToken();
            }
        }

        for (int i = 1; i <= spotifyService.getAllTokens().size() ; i++) {
            if (spotifyService.getTokenById(i).getExpirationTime(i).compareTo(LocalDateTime.now()) > 0)
            accessToken = spotifyService.getTokenById(i);
        }
        
        return accessToken;
    }

    public @ResponseBody Token requestAndSaveAccessToken() {
        String response = getWebClientBuilder.build()
            .method(HttpMethod.POST)
            .uri("/api/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
            .retrieve()
            .bodyToMono(String.class)
            .block();
        Token responseToken = dividingResponse(response);
        spotifyService.saveToken(responseToken);

        return responseToken;
    }

    // ToDo String to JSON jackson
    public @ResponseBody Token dividingResponse(String response) {
        String[] divided = response.split(",");
        String[] dividedAccessToken = divided[0].split(":");
        String[] dividedTokenType = divided[1].split(":");
        String[] dividedExpiresIn = divided[2].split(":");
        String[] dividedScope = divided[3].split(":");

        String accessToken = dividedAccessToken[1].trim().substring(1,  dividedAccessToken[1].length() - 1);
        String tokenType = dividedTokenType[1].trim().substring(1, (dividedTokenType[1].length() - 1));
        int expiresIn = Integer.valueOf(dividedExpiresIn[1]);
        String scope = dividedScope[1].trim().substring(1, (dividedScope[1].length() - 2));

        Token responseToken = new Token(accessToken, tokenType, expiresIn, scope);
        final int diff = responseToken.getExpirationTime(1).compareTo(LocalDateTime.now());
        System.out.println(diff);
        return responseToken;

    }
    
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("Querying url {}", clientRequest.url());
            
            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder("Request: \n");
                clientRequest
                    .headers()
                    .forEach((name, values) -> values.forEach(value -> sb.append(value)));
                logger.info(sb.toString());
            }
            return Mono.just(clientRequest);
        });
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder()
        .baseUrl("https://accounts.spotify.com")
        .defaultHeaders(header -> header.setBasicAuth(client_id, clientSecret))
        .filter(logRequest());
    }
}
