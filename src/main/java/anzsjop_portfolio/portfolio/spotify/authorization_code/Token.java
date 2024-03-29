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
    private LocalDateTime expiration_time;

    public Token() {
        
    }

    public Token(String access_token, String token_type, int expires_in, String scope) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.expiration_time = LocalDateTime.now().plusSeconds(expires_in);
    }

    public Token(HashMap<String, Object> map) {
        this.access_token = map.get("access_token").toString();
        this.token_type = map.get("token_type").toString();
        this.expires_in = Integer.valueOf(map.get("expires_in").toString());
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

    public void setExpirationTime(LocalDateTime expiration_time) {
        this.expiration_time = expiration_time;
    }

}
