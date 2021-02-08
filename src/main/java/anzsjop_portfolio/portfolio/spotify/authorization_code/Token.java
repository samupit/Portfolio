package anzsjop_portfolio.portfolio.spotify.authorization_code;

import java.time.LocalDateTime;
import java.util.HashMap;
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

    public Token(HashMap<String, Object> map) {
        this.access_token = map.get("access_token").toString();
        this.token_type = map.get("token_type").toString();
        this.expires_in = Integer.valueOf(map.get("expires_in").toString());
        this.scope = map.get("scope").toString();
        this.expiration_time = LocalDateTime.now().plusSeconds(expires_in);
        
    }

    public Integer getId() {
        return id;
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getTokenType() {
        return token_type;
    }

    public int getExpiresIn() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }

    public LocalDateTime getExpirationTime() {
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
