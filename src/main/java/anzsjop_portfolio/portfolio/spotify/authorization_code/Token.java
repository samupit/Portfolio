package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id;
    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String scope;
    private LocalDateTime expirationTime;

    public Token() {
        
    }
    //ToDo juokseva id ja expire date
    public Token(String accessToken, String tokenType, int expiresIn, String scope) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.scope = scope;
        this.expirationTime = LocalDateTime.now();
        
    }

    public Integer getId() {
        return id;
    }

    public String getAccessToken(Integer id) {
        return accessToken;
    }

    public String getTokenType(Integer id) {
        return tokenType;
    }

    public int getExpiresIn(Integer id) {
        return expiresIn;
    }

    public String getScope(Integer id) {
        return scope;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
