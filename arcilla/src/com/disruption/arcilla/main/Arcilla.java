package com.disruption.arcilla.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.disruption.arcilla.httphandler.DelegatorHandler;
import com.disruption.arcilla.httphandler.RequestHandler;
import com.sun.net.httpserver.HttpServer;

public class Arcilla {

	protected final HttpServer httpServer;
	private final static int HTTP_DEFAULT_PORT = 80;
	private final static int USE_DEFAULT_BACKLOG_SIZE = 0;

	private final static int MINIMUM_VALID_PORT = 0;
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

	public void startServer() {
		httpServer.start();
	}

	public void addRestEndpointRequestHandler(String restEndpointRelativePath, RequestHandler requestHandler) {
		httpServer.createContext(restEndpointRelativePath, new DelegatorHandler(requestHandler));
	}
	
	public void removeRestEndPointRequestHandler(String restEndpointRelativePath){
		httpServer.removeContext(restEndpointRelativePath);
	}

	public void stopServer() {
		stopServer(0);
	}

	public void stopServer(int maximumDelay) {
		httpServer.stop(maximumDelay);
	}
	
	protected HttpServer createServer(InetSocketAddress inetSocketAddress, int backlogSize) throws IOException{
		return HttpServer.create(inetSocketAddress, backlogSize);
	}

}
