package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.NoRequestBodyException;
import play.mvc.Controller;
import utils.AppUtils;
import utils.MsgKeys;

public class BaseController extends Controller {

    private final AppUtils appUtils;

    public BaseController(AppUtils appUtils) {
        this.appUtils = appUtils;
    }

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

}
