package maf.acceptance

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import java.io.Closeable
import javax.servlet.Servlet

class ApplicationServer(port: Int, private val servlet: Servlet) : Closeable {

    private val _server: Server = Server(port)

    fun start(): ApplicationServer {
        val handler = ServletContextHandler()
        handler.addServlet(ServletHolder(servlet), "/*")
        _server.handler = handler
        _server.start()

        return this;
    }

    override fun close() {
        _server.stop()
    }
}