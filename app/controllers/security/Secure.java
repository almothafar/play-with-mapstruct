package controllers.security;

import com.google.inject.Inject;
import models.User;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import services.UserService;


public class Secure extends Security.Authenticator {

    @Inject
    private UserService userService;

    /**
     * authenticate the session and check if his session expired before accessing any protected API
     *
     * @param ctx http context contains the user session
     * @return the user session if he have a valid one and null if not.
     */
    @Override
    public String getUsername(Http.Context ctx) {


        String userId = ctx.session().get("userId");

        if (userId == null) {
            ctx.session().clear();
            return null;
        }

        User user = userService.findUserById(Integer.parseInt(userId));
        if (user != null && user.isActive()) {
            return ctx.session().get("user_id");
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return unauthorized("User not authorized");
    }
}
