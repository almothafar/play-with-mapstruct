package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import controllers.security.CheckPassword;
import controllers.security.Secure;
import mappers.UserMapper;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import services.UserService;


public class UsersController extends BaseController {
    private final UserService userService;

    @Inject
    public UsersController(UserService userService, MessagesApi messagesApi) {
        super(messagesApi);
        this.userService = userService;
    }


    @Security.Authenticated(Secure.class)
    @With({CheckPassword.class})
    public Result getLoggedInUserDetails() {
        return ok(getUserObjectNode(Integer.parseInt(session().get("userId"))));
    }

    public Result login() {
        session().clear();
        session("userId", String.valueOf(1));

        return ok(getUserObjectNode(Integer.parseInt(session().get("userId"))));
    }

    private JsonNode getUserObjectNode(int userId) {
        return Json.toJson(UserMapper.INSTANCE.map(userService.findUserById(userId)));
    }

}
