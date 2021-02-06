package anzsjop_portfolio.portfolio.spotify.authorization_code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SpotifyService {

    private static final Logger logger = LogManager.getLogger(SpotifyController.class);

    private static final String client_id = "c251e8621f594d1b9103deede890cbbc";
    private static final String clientSecret = "25d0dd118b3649b68a1b27f3aace65c6";
    
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    WebClient.Builder getWebClientBuilder;

    public ArrayList<Token> getAllTokens() {
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokenRepository.findAll().forEach(token -> tokens.add(token));
        return tokens;
    }

    public Token getNewestToken() {
        Token newestToken = new Token();
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokenRepository.findAll().forEach(token -> tokens.add(token));
        newestToken = tokens
            .stream()
            .max(Comparator.comparing(Token::getId))
            .orElseThrow(NoSuchElementException::new);
        return newestToken;
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
        saveToken(responseToken);

        return responseToken;
    }

    public HashMap<String, Object> jsonStringToMap(String jsonResponse) {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> accessTokenHashMap = new HashMap<String, Object>();
        try {
            accessTokenHashMap = mapper.readValue(jsonResponse, new TypeReference<HashMap<String, Object>>() {});
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

    public Token getTokenById(Integer id) {
        return tokenRepository.findById(id).get();
    }

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public void delete(Integer id) {
        tokenRepository.deleteById(id);
    }

}
