package filters;

import com.google.inject.Inject;
import play.http.DefaultHttpFilters;


public class AppFilters extends DefaultHttpFilters {

    @Inject
    public AppFilters(TSGzipFilter gzipFilter, TSNoCacheFilter noCacheFilter) {
        super(noCacheFilter.asJava(), gzipFilter);
    }
}