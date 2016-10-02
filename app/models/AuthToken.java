package models;

import javax.persistence.*;


@Entity
@Table(name = "auth_tokens")
public class AuthToken extends BaseModel {

    @OneToOne(targetEntity = User.class, cascade = CascadeType.REFRESH)
    private User user;

    @Column(nullable = false)
    private String token;

    @Column
    private long expireAfter;

    public AuthToken() {
    }

    public User getUser() {
        return user;
    }

    public AuthToken setUser(User user) {
        this.user = user;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthToken setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpireAfter() {
        return expireAfter;
    }

    public AuthToken setExpireAfter(long expireAfter) {
        this.expireAfter = expireAfter;
        return this;
    }
}
