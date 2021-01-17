package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.util.ArrayList;

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
