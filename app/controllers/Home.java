package controllers;

import com.google.inject.Inject;
import play.Logger;
import play.mvc.Result;
import utils.AppUtils;

import java.time.LocalDateTime;

public class Home extends BaseController {

//    private final GulpAssets gulpAssets;

    @Inject
    public Home(AppUtils appUtils) {
        super(appUtils);
//        this.gulpAssets = gulpAssets;
    }

//    public play.api.mvc.Action<AnyContent> redirectToUI(String any) {
//        return gulpAssets.index();
//    }

    public Result saveFrontendException() {
        String exception = getRequestBody().get("exception").asText();
        Logger.warn(">>>> UI Exception: " + exception + " time:" + LocalDateTime.now().toString());
        return ok();
    }
}
