package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import controllers.viewModels.BaseDTO;
import exceptions.NoRequestBodyException;
import org.jetbrains.annotations.NotNull;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AppUtils;
import utils.MsgKeys;

public class BaseController extends Controller {

    @Inject
    private
    AppUtils appUtils;

    private String getMessage(String key, Object... args) {
        return appUtils.getMessage(key, args);
    }

    JsonNode getRequestBody() throws NoRequestBodyException {
        JsonNode requestBody = request().body().asJson();
        if (requestBody == null) {
            throw new NoRequestBodyException(getMessage(MsgKeys.ERR_NO_FORM_DATA));
        }
        return request().body().asJson();
    }

    @NotNull
    Result ok(BaseDTO baseDTO) {
        return ok(Json.toJson(baseDTO));
    }

}
