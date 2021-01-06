package anzsjop_portfolio.portfolio.spotify.authorization_code;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Integer> {
    
}
