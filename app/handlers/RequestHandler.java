package handlers;

import com.google.inject.Inject;
import play.Logger;
import play.api.mvc.Handler;
import play.http.HandlerForRequest;
import play.http.HttpRequestHandler;
import play.libs.streams.Accumulator;
import play.mvc.*;
import play.routing.Router;
import utils.AppLogger;
import utils.AppUtils;

/**
 * Created by Al-Mothafar Al-Hasan on 10/29/15.
 */
public class RequestHandler implements HttpRequestHandler {
    private final AppLogger.ALogger accessLogger = Logger.of(RequestHandler.class);
    private final Router router;

    @Inject
    public RequestHandler(Router router) {
        this.router = router;
    }

    @Override
    public HandlerForRequest handlerForRequest(Http.RequestHeader request) {
        accessLogger.debug(AppUtils.concatStrings("Exception: method=", request.method(),
                " uri=", request.uri(), " remote-address=", request.remoteAddress()));

        Handler handler = router.route(request).orElseGet(() ->
                EssentialAction.of(req -> Accumulator.done(Results.notFound()))
        );
        return new HandlerForRequest(request, handler);
    }

//    @Override
//    public Action<?> createAction(Http.Request request, Method actionMethod) {
//        accessLogger.debug(TimesheetUtils.concatStrings("Exception: method=", request.method(),
//                " uri=", request.uri(), " remote-address=", request.remoteAddress()));
//        /*return new Action.Simple() {
//            @Override
//            public F.Promise<Result> call(Http.Context ctx) throws Throwable {
//                User user = play.Play.application().injector().instanceOf(UserService.class).findUserById(Integer.parseInt(ctx.session().get(ViewModelsConstants.User.USER_ID)));
//                // if the action is annotated with @NoAuthRequired or user is logged in delegate to it
//                if (actionMethod.isAnnotationPresent(NoAuthRequired.class) || ctx.session().containsKey(ViewModelsConstants.User.USER_ID)) {
//                    // IF the following condition is true, then its here becasue there must be Authorization and session has user id, so need to check things
//                    if (!actionMethod.isAnnotationPresent(NoAuthRequired.class)) {
//                        if (actionMethod.isAnnotationPresent(AdminRequired.class) && !user.is_admin) {
//                            return F.Promise.pure(forbidden(Messages.get(MsgKeys.USER_NOT_AUTHORIZED)));
//                        }
//                        if (!user.password_updated) {
//                            return F.Promise.pure(unauthorized(Messages.get(MsgKeys.USER_SHOULD_UPDATE_PASSWORD)));
//                        }
//                    }
//                    // if passed all conditions then everything is ok.
//                    return delegate.call(ctx);
//                }
//                // otherwise, block access
//                else {
//                    return F.Promise.pure(unauthorized(Messages.get(MsgKeys.USER_NOT_AUTHENTICATED)));
//                }
//            }
//        };*/
//        return new Action.Simple() {
//            @Override
//            public CompletionStage<Result> call(Http.Context ctx) {
//                return delegate.call(ctx);
//
//            }
//        };
//    }
}
