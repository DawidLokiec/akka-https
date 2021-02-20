package de.htw_berlin.f4.server.dip

/**
 * This trait defines the methods of a HTTP request handler.
 */
trait RequestHandler {

  /**
   * Returns an instance of Route.
   *
   * @return an instance of Route.
   */
  def getRoute: akka.http.scaladsl.server.Route

}