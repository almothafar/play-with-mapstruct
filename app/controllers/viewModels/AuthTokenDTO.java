package controllers.viewModels;

public class AuthTokenDTO extends BaseDTO {
    private UserDTO user;
    private String token;
    private long expireAfter;

    public AuthTokenDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public AuthTokenDTO setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthTokenDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpireAfter() {
        return expireAfter;
    }

    public AuthTokenDTO setExpireAfter(long expireAfter) {
        this.expireAfter = expireAfter;
        return this;
    }
}
