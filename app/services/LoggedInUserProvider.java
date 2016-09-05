package services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.config.CurrentUserProvider;
import models.User;
import play.mvc.Http;

public class LoggedInUserProvider implements CurrentUserProvider {

    @Override
    public User currentUser() {
        String userId;
        Http.Context.current().session();
        if (Http.Context.current() != null && (userId = Http.Context.current().session().get("userId")) != null) {
            return Ebean.getReference(User.class, Integer.parseInt(userId));
        }
        return null;
    }
}
