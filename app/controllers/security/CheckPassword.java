package controllers.security;

import com.google.inject.Inject;
import models.User;
import play.i18n.Lang;
import play.i18n.MessagesApi;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import services.UserService;
import utils.MsgKeys;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class CheckPassword extends Action.Simple {

    @Inject
    private UserService userService;

    @Inject
    private MessagesApi messagesApi;

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        User user = userService.findUserById(Integer.parseInt(ctx.session().get("userId")));
        if (user.isPasswordUpdated())
            return delegate.call(ctx);
        else {
            return CompletableFuture.completedFuture(unauthorized(messagesApi.get(Lang.defaultLang(), MsgKeys.USER_SHOULD_UPDATE_PASSWORD)));
        }
    }
}
