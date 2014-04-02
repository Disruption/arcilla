package com.disruption.arcilla.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

public class SecureArcilla extends Arcilla {

	public SecureArcilla() throws IOException {
		super();
	}

	public SecureArcilla(InetAddress inetAddress, int port) throws IOException {
		super(inetAddress, port);
	}

	public SecureArcilla(InetAddress inetAddress) throws IOException {
		super(inetAddress);
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
