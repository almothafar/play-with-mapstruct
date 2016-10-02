package models.users;

import models.User;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Twitter")
public class TwitterUser extends User {

    @Column(length = 15, unique = true, name = "twitter_screen_name")
    private String screenName;

    public String getScreenName() {
        return screenName;
    }

    public TwitterUser setScreenName(String screenName) {
        this.screenName = screenName;
        return this;
    }
}
