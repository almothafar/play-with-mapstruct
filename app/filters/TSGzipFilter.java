package filters;

/**
 * a filter to compress json response
 * Created by amer.idries on 12/16/2014.
 */

import akka.stream.Materializer;
import com.google.inject.Inject;
import play.filters.gzip.GzipFilter;
import play.filters.gzip.GzipFilterConfig;
import play.mvc.EssentialAction;
import play.mvc.EssentialFilter;
import play.mvc.Http;
import play.mvc.Result;

import java.util.function.BiFunction;

public class TSGzipFilter extends EssentialFilter {

    final private GzipFilter filter;

    @Inject
    public TSGzipFilter(Materializer materializer) {
        this.filter = new GzipFilter(
                new GzipFilterConfig().withChunkedThreshold(102400).withBufferSize(102400).withShouldGzip((BiFunction<Http.RequestHeader, Result, Object>) (req, res) -> {
                            String type = res.body().contentType().orElse("");
                            return type.startsWith("application/json") ||
                                    type.startsWith("application/javascript") ||
                                    type.startsWith("text/css") ||
                                    type.startsWith("text/html") ||
                                    type.startsWith("application/vnd.ms-fontobject") ||
                                    type.startsWith("application/x-font-ttf") ||
                                    type.startsWith("image/svg+xml");
                        }
                ), materializer
        );
    }


    @Override
    public EssentialAction apply(EssentialAction next) {
        return filter.asJava().apply(next);
    }
}