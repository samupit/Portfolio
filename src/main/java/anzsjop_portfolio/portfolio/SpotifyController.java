package anzsjop_portfolio.portfolio;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;



@Controller
public class SpotifyController {

    @RequestMapping("/")
	public @ResponseBody String greeting() {
        WebClient webClient = WebClient
            .builder()
                .baseUrl("https://accounts.spotify.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "https://accounts.spotify.com"))
            .build();
        
        WebClient.UriSpec<WebClient.RequestBodySpec> request = webClient
            .method(HttpMethod.GET);
        WebClient.RequestBodySpec uri = webClient
            .method(HttpMethod.GET)
            .uri("/authorize");
        WebClient.RequestHeadersSpec requestSpec = webClient
            .create()
            .method(HttpMethod.GET)
            .uri("/authorize")
            .body(BodyInserters.fromPublisher(Mono.just("data")), String.class);
        BodyInserter<Publisher<String>, ReactiveHttpOutputMessage> inserter = BodyInserters
            .fromPublisher(Subscriber::onComplete, String.class);
        WebClient.ResponseSpec response = uri
            .body(inserter)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(Charset.forName("UTF-8"))
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
            .retrieve();
        
    }
    
}
