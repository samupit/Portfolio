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
    private String access_token;
    private String token_type;
    private int expires_in;
    private String scope;
    private LocalDateTime expiration_time;

    public Token() {
        
    }

    public Token(String access_token, String token_type, int expires_in, String scope) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.scope = scope;
        this.expiration_time = LocalDateTime.now().plusSeconds(expires_in);
        
    }

    public Integer getId() {
        return id;
    }

    public String getAccessToken(Integer id) {
        return access_token;
    }

    public String getTokenType(Integer id) {
        return token_type;
    }

    public int getExpiresIn(Integer id) {
        return expires_in;
    }

    public String getScope(Integer id) {
        return scope;
    }

    public LocalDateTime getExpirationTime(Integer id) {
        return expiration_time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public void setTokenType(String token_type) {
        this.token_type = token_type;
    }

    public void setExpiresIn(int expires_in) {
        this.expires_in = expires_in;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setExpirationTime(LocalDateTime expiration_time) {
        this.expiration_time = expiration_time;
    }

}
