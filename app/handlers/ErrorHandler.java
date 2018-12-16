package handlers;

import com.typesafe.config.Config;
import exceptions.ApplicationException;
import exceptions.BusinessException;
import exceptions.NoRequestBodyException;
import play.Environment;
import play.Logger;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ErrorHandler extends DefaultHttpErrorHandler {
    private final Logger.ALogger errorLogger = Logger.of(ErrorHandler.class);

    @javax.inject.Inject
    public ErrorHandler(Config configuration, Environment environment,
                        OptionalSourceMapper sourceMapper, javax.inject.Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    @Override
    protected CompletionStage<Result> onProdServerError(Http.RequestHeader request, UsefulException exception) {
        if (exception instanceof NoRequestBodyException) {
            return CompletableFuture.completedFuture(
                    Results.badRequest(exception.getMessage()));
        } else if (exception instanceof BusinessException) {
            return CompletableFuture.completedFuture(
                    Results.badRequest(exception.getMessage()));
        } else if (exception instanceof ApplicationException) {
            exception.printStackTrace();
            return CompletableFuture.completedFuture(
                    Results.badRequest(exception.getMessage()));
        }
        exception.printStackTrace();
        return CompletableFuture.completedFuture(
                Results.internalServerError("A server error occurred: " + exception.getMessage())
        );
    }

    @Override
    protected CompletionStage<Result> onForbidden(Http.RequestHeader request, String message) {
        return CompletableFuture.completedFuture(
                Results.forbidden("You're not allowed to access this resource.")
        );
    }

    @Override
    protected CompletionStage<Result> onDevServerError(Http.RequestHeader request, UsefulException exception) {
        errorLogger.error("Exception: method: " + request.path() + " time:" + LocalDateTime.now().toString() +
                " uri=" + request.uri() + " remote-address=" + request.remoteAddress() + "cause: " + exception.getMessage());
        if (exception instanceof NoRequestBodyException) {
            return CompletableFuture.completedFuture(
                    Results.badRequest(exception.getMessage()));
        } else if (exception instanceof BusinessException) {
            return CompletableFuture.completedFuture(
                    Results.badRequest(exception.getMessage()));
        } else if (exception instanceof ApplicationException) {
            return CompletableFuture.completedFuture(
                    Results.badRequest(exception.getMessage()));
        }
//        return CompletableFuture.completedFuture(
//                Results.internalServerError(exception.getMessage()));
        return super.onDevServerError(request, exception);
    }

}