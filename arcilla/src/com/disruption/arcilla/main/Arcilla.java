package com.disruption.arcilla.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.disruption.arcilla.httphandler.DelegatorHandler;
import com.disruption.arcilla.httphandler.RequestHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Main Arcilla Class in charge of creating the actual HttpServer and add or remove REST endpoints
 *
 * @author Disruption <stomasortiz@gmail.com>
 */
public class Arcilla {

	protected final HttpServer httpServer;
    /**
     * Standard HTTPS port
     */
	private final static int HTTP_DEFAULT_PORT = 80;
    /**
     * Default backlog size
     */
	private final static int USE_DEFAULT_BACKLOG_SIZE = 0;

    /**
     * Minimum valid port value, indicating the system to pick one
     */
	private final static int MINIMUM_VALID_PORT = 0;
    /**
     * Minimum allowed port value
     */
	private final static int MAXIMUM_VALID_PORT = 65535;

	public Arcilla() throws IOException {
		this(InetAddress.getLoopbackAddress(), HTTP_DEFAULT_PORT);
	}

	public Arcilla(InetAddress inetAddress) throws IOException {
		this(inetAddress, HTTP_DEFAULT_PORT);
	}

	public Arcilla(int port) throws IOException {
		this(InetAddress.getLoopbackAddress(), port);
	}

	public Arcilla(InetAddress inetAddress, int port) throws IOException {
		if (inetAddress == null) {
			throw new IllegalArgumentException(
					"Provided InetAddress can not be null, use empty constructor or port constructor instead");
		}

		if (port < MINIMUM_VALID_PORT || port > MAXIMUM_VALID_PORT) {
			throw new IllegalArgumentException("Provided port must be on the range [" + MINIMUM_VALID_PORT + " , "
					+ MAXIMUM_VALID_PORT + "]");
		}

		httpServer = createServer(new InetSocketAddress(inetAddress, port), USE_DEFAULT_BACKLOG_SIZE);
	}

	public Arcilla(InetSocketAddress inetSocketAddress) throws IOException {
		if (inetSocketAddress == null) {
			throw new IllegalArgumentException(
					"Provided InetSocketAddress can not be null, use empty constructor instead");
		}
		httpServer = createServer(inetSocketAddress, USE_DEFAULT_BACKLOG_SIZE);
	}

    /**
     * Starts the httpServer, which runs on a separate thread
     */
	public void startServer() {
		httpServer.start();
	}

    /**
     * Adds a rest endpoint for this server to process
     * @param restEndpointRelativePath Relative path to listen to
     * @param requestHandler Handler that will process the requests
     */
	public void addRestEndpointRequestHandler(String restEndpointRelativePath, RequestHandler requestHandler) {
		httpServer.createContext(restEndpointRelativePath, new DelegatorHandler(requestHandler));
	}

    /**
     * Remove a rest endpoint for this server to process
     * @param restEndpointRelativePath Relative path to remove from list
     */
	public void removeRestEndPointRequestHandler(String restEndpointRelativePath){
		httpServer.removeContext(restEndpointRelativePath);
	}

    /**
     * Stops immediately the current arcilla instance. After this call, the server can not be
     * reused, so it should be used called only when exiting or when it's not required any more
     */
	public void stopServer() {
		stopServer(0);
	}

    /**
     * Stops the current arcilla instance in as much as the maximum delay specified.
     * After this call, the server can not be
     * reused, so it should be used called only when exiting or when it's not required any more
     *
     * @param maximumDelay Maximum delay in milliseconds to stop the server
     */
	public void stopServer(int maximumDelay) {
		httpServer.stop(maximumDelay);
	}

    /**
     * Creates the actual instance of the http server with the given parameters
     *
     * @param inetSocketAddress InetSocketAddress to bind the HttpServer to
     * @param backlogSize Backlog size for this server instance
     * @return HttpServer instance
     * @throws IOException
     */
	protected HttpServer createServer(InetSocketAddress inetSocketAddress, int backlogSize) throws IOException{
		return HttpServer.create(inetSocketAddress, backlogSize);
	}

}
