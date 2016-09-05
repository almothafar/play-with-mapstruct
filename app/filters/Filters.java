package filters;

import com.google.inject.Inject;
import play.http.HttpFilters;
import play.mvc.EssentialFilter;

/**
 * Created by almothafar on 7/14/2016.
 */
public class Filters implements HttpFilters {
    private EssentialFilter[] filters;

    @Inject
    public Filters(TSGzipFilter gzipFilter, TSNoCacheFilter noCacheFilter) {
        filters = new EssentialFilter[]{noCacheFilter.asJava(), gzipFilter.asJava()};
    }

    @Override
    public play.mvc.EssentialFilter[] filters() {
        return filters;
    }
}
