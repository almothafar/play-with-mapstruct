package services;

import com.google.inject.Inject;
import utils.AppUtils;


public class BaseService {

    @Inject
    AppUtils appUtils;
    @Inject
    private UserService userService;

    public String getMessage(String key, Object... args) {
        return appUtils.getMessage(key, args);
    }

    UserService getUserService() {
        return userService;
    }


}
