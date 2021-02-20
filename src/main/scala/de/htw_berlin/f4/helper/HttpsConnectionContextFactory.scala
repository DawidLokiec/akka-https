package de.htw_berlin.f4.helper

/**
 * Contains a helper factory method to create a HttpsConnectionContext from a given
 * .p12 key store and the key store's password.
 */
object HttpsConnectionContextFactory {


  import akka.http.scaladsl.{ConnectionContext, HttpsConnectionContext}

  import java.io.FileInputStream
  import java.security.SecureRandom
  import javax.net.ssl.SSLContext

  /**
   * Creates a HTTPS connection context from the passed <b>.p12</b> key store and the key store's password.
   *
   * @param keyStoreFilename the absolute path to the key store.
   * @param keyStorePassword the key store's password.
   * @return a HTTPS connection context
   */
  def apply(keyStoreFilename: String, keyStorePassword: String): HttpsConnectionContext = {
    val p12KeyStore = PKCS12KeyStoreFactory(new FileInputStream(keyStoreFilename), keyStorePassword)
    val sslContext: SSLContext = SSLContext.getInstance("TLS")
    sslContext.init(X509KeyManagersFactory(p12KeyStore, keyStorePassword), TrustManagersFactory(p12KeyStore), new SecureRandom)
    ConnectionContext.httpsServer(sslContext)
  }

}
