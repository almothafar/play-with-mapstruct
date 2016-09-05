package controllers;

import com.google.inject.Inject;
import org.joda.time.DateTime;
import play.Logger;
import play.api.mvc.Action;
import play.api.mvc.AnyContent;
import play.mvc.Controller;
import play.mvc.Result;

public class Home extends BaseController {

    @Inject
    private com.tuplejump.playYeoman.Yeoman yeoman;

    public Action<AnyContent> redirectToUI(String any) {
        return yeoman.at("index.html");
    }

    public Result saveFrontendException() {
        String exception = getRequestBody().get("exception").asText();
        Logger.warn(">>>> UI Exception: " + exception + " time:" + DateTime.now().toString());
        return ok();
    }
}
