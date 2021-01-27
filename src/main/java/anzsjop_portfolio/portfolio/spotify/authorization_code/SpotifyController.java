package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.type.TypeReference;
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
        int latestTokenId = spotifyService.getAllTokens().size();
        if (spotifyService.getAllTokens().isEmpty()) {
            accessToken = requestAndSaveAccessToken();
        } else if (spotifyService.getTokenById(latestTokenId)
            .getExpirationTime(latestTokenId)
            .compareTo(LocalDateTime.now()) <= 0) {
            accessToken = requestAndSaveAccessToken();
        } else {
            accessToken = spotifyService.getTokenById(latestTokenId);
        }
        
        return accessToken;
    }

    public @ResponseBody Token requestAndSaveAccessToken() {
        String jsonResponse = getWebClientBuilder.build()
            .method(HttpMethod.POST)
            .uri("/api/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
            .retrieve()
            .bodyToMono(String.class)
            .block();
        Token responseToken = new Token(jsonStringToMap(jsonResponse));
        spotifyService.saveToken(responseToken);

        return responseToken;
    }

    public HashMap<String, Object> jsonStringToMap(String jsonResponse) {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> accessTokenHashMap = new HashMap<String, Object>();
        try {
            accessTokenHashMap = mapper.readValue(jsonResponse, new TypeReference<HashMap<String, Object>>() {});
            System.out.println(accessTokenHashMap);
            System.out.println("using entrySet and manual string creation");

            for (Entry<String, Object> entry : accessTokenHashMap.entrySet()) {
                System.out.println(entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessTokenHashMap;
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
