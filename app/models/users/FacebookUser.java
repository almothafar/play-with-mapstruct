package models.users;

import models.User;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Facebook")
public class FacebookUser extends User {

    @Column(length = 128, unique = true, name = "facebook_user_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public FacebookUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
