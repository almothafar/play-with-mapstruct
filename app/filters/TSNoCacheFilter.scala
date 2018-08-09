package filters

import com.google.inject.Inject
import play.api.mvc.{EssentialAction, EssentialFilter, RequestHeader}

import scala.concurrent.ExecutionContext

/**
  * a filter to set the cache-control of response headers
  * Created by amer.idries on 12/23/2014.
  */
class TSNoCacheFilter @Inject()(implicit ec: ExecutionContext) extends EssentialFilter {
  def apply(nextFilter: EssentialAction) = new EssentialAction {
    def apply(requestHeader: RequestHeader) = {

      nextFilter(requestHeader).map { result =>
        if (result.header.headers.get("Content-Type").isDefined && result.header.headers("Content-Type").startsWith("application/json")) {
          result.withHeaders("Cache-Control" -> "no-cache")
        } else {
          result
        }
      }
    }
  }
}
