package utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.api.i18n.Lang;
import play.i18n.MessagesApi;


@Singleton
public class AppUtils {

    @Inject
    private MessagesApi messagesApi;

    @Inject
    public AppUtils() {

    }


    public static String concatStrings(Object... args) {
        String fullString = "";
        if (args != null) {
            for (Object str : args) {
                if (str != null) {
                    fullString = fullString.concat(String.valueOf(str));
                }
            }
        }
        return fullString;
    }

    public String getMessage(String key, Object... args) {
        return messagesApi.get(Lang.defaultLang(), key, args);
    }
}
