package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.NoRequestBodyException;
import play.api.i18n.Lang;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import utils.MsgKeys;

public class BaseController extends Controller {

    private final MessagesApi messagesApi;

    public BaseController(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    JsonNode getRequestBody() throws NoRequestBodyException {
        JsonNode requestBody = request().body().asJson();
        if (requestBody == null) {
            throw new NoRequestBodyException(messagesApi.get(Lang.defaultLang(), MsgKeys.ERR_NO_FORM_DATA));
        }
        return request().body().asJson();
    }

}
