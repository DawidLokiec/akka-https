package de.htw_berlin.f4.server

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.{Http, HttpsConnectionContext}

/**
 * Represents a HTTPS server which can be bind to a host and port.
 * The server needs a request handler for processing incoming requests.
 *
 * @param host         the optional host of the server. If not present, then the default value is 0.0.0.0.
 * @param port         the optional port of the server. The default value is 443.
 * @param enableCors   a flag in order to indicate if CORS policy should consindered in the application.conf file.
 * @param actorSystem  an implicit actor system.
 * @param httpsContext an implicit HTTPS connection context in order to enable the HTTPS connections.
 * @see de.htw_berlin.f4.server.dip.RequestHandler
 */
class Server(val host: String = "0.0.0.0",
             val port: Int = 443,
             val enableCors: Boolean = false
            )(implicit val actorSystem: ActorSystem[Nothing], implicit val httpsContext: HttpsConnectionContext) {

  import scala.concurrent.Future
  import akka.http.scaladsl.Http.ServerBinding
  import de.htw_berlin.f4.server.dip.RequestHandler

  /**
   * Binds the current server to the host and port and uses the passed handler for processing incoming connections.
   *
   * @param requestHandler the request handler to be used for processing incoming requests.
   * @return a server binding future.
   */
  def bindAndHandleWith(requestHandler: RequestHandler): Future[ServerBinding] = {
    import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
    val route =
      if (enableCors) cors() {
        requestHandler.getRoute
      } else
        requestHandler.getRoute
    Http().newServerAt(host, port).enableHttps(httpsContext).bind(route)
  }
}