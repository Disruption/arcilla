package com.disruption.arcilla.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

public class SecureArcilla extends Arcilla {
    private final static int HTTPS_DEFAULT_PORT = 443;

	public SecureArcilla() throws IOException {
		this(HTTPS_DEFAULT_PORT);
	}

	public SecureArcilla(InetAddress inetAddress, int port) throws IOException {
		super(inetAddress, port);
	}

	public SecureArcilla(InetAddress inetAddress) throws IOException {
		this(inetAddress, HTTPS_DEFAULT_PORT);
	}

	public SecureArcilla(InetSocketAddress inetSocketAddress) throws IOException {
		super(inetSocketAddress);
	}

	public SecureArcilla(int port) throws IOException {
		super(port);
	}

	public HttpsConfigurator getHttpsConfigurator() {
		return ((HttpsServer) httpServer).getHttpsConfigurator();
	}
	
	protected HttpServer createServer(InetSocketAddress inetSocketAddress, int backlogSize) throws IOException{
		return HttpsServer.create(inetSocketAddress, backlogSize);
	}

}
