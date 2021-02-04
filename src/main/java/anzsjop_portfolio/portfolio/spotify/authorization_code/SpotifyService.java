package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {
    
    @Autowired
    TokenRepository tokenRepository;

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
