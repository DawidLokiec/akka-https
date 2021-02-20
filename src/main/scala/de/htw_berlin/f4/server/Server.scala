package de.htw_berlin.f4.server

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.{Http, HttpsConnectionContext}

/**
 * Represents a HTTPS server which can be bind to a host and port.
 * The server needs a request handler for processing incoming requests.
 *
 * @param host         the optional host of the server. If not present, then the default value is 0.0.0.0.
 * @param port         the optional port of the server. The default value is 443.
 * @param httpsContext the HTTPS connection context in order to enable the HTTPS connections.
 * @param actorSystem  an implicit actor system.
 * @see RequestHandler
 */
class Server(val host: String = "0.0.0.0",
             val port: Int = 443,
             private val httpsContext: HttpsConnectionContext)(implicit val actorSystem: ActorSystem[Nothing]) {

  import scala.concurrent.Future
  import akka.http.scaladsl.Http.ServerBinding
  import de.htw_berlin.f4.server.dip.RequestHandler

  /**
   * Binds the current server to the host and port and uses the passed handler for processing incoming connections.
   *
   * @param requestHandler the request handler to be used for processing incoming requests.
   * @return a server binding future.
   */
  def bindAndHandleWith(requestHandler: RequestHandler): Future[ServerBinding] =
    Http().newServerAt(host, port).enableHttps(httpsContext).bind(requestHandler.getRoute)
}