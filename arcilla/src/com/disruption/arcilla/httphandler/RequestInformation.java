package com.disruption.arcilla.httphandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

/**
 * Class in charge of wrapping the HttpExchange object to avoid exposing it to the RequestHandler
 * by providing accessor methods to all required fields.
 *
 * @author Disruption <stomasortiz@gmail.com>
 */
public final class RequestInformation {

	private final HttpExchange httpExchange;
	private String requestBody;
	
	protected RequestInformation(HttpExchange httpExchange){
		this.httpExchange = httpExchange;
	}

    /**
     * Provides the requested attribute from the exchange
     * @param attributeName Attribute key for the lookup
     * @return Attribute value or null if not found
     */
	public Object getRequestAttribute(String attributeName) {
		return httpExchange.getAttribute(attributeName);
	}

    /**
     * Provides the request Headers
     * @return Headers for the request
     */
	public Headers getRequestHeaders() {
		return httpExchange.getRequestHeaders();
	}

    /**
     * Provides the request method used (GET, POST, etc.)
     * @return String containing the request method
     */
	public String getRequestMethod() {
		return httpExchange.getRequestMethod();
	}

    /**
     * Provides the URI associated with the request
     * @return URI item for this request
     */
	public URI getRequestURI() {
		return httpExchange.getRequestURI();
	}

    /**
     * Provides the request body as a string
     * @return String containing the request body. Returns empty string if empty, never null
     */
	public String getRequestBody(){
		if (requestBody == null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			
			try {
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			requestBody = stringBuilder.toString();
		}
		
		return requestBody;
		
	}
}
