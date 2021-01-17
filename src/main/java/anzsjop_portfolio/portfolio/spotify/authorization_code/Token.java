package anzsjop_portfolio.portfolio.spotify.authorization_code;

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

    public Token() {
        
    }

    public Token(String access_token) {
        this.id = 1;
        this.access_token = access_token;
    }

	public int getId() {
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

}
