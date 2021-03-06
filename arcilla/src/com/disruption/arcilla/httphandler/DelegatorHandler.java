package com.disruption.arcilla.httphandler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Class in charge of wrapping the request handler and doing the low level operations that
 * are repeated for any request processed, like sending the headers, writing the response body
 * and closing the exchange.
 *
 * @author Disruption <stomasortiz@gmail.com>
 */
public class DelegatorHandler implements HttpHandler {

	private final RequestHandler requestHandler;
	
	public DelegatorHandler(RequestHandler requestHandler){
		this.requestHandler = requestHandler;
	}
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		RequestInformation requestInformation = new RequestInformation(httpExchange);
		HttpResponse response = requestHandler.processRequest(requestInformation);
		String responseBody = response.getResponseBody();
		
		httpExchange.sendResponseHeaders(response.getResponseCode(), responseBody.length());
		httpExchange.getResponseBody().write(responseBody.getBytes());
		httpExchange.close();
	}

}
