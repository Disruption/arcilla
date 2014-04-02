package com.disruption.arcilla.httphandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public final class RequestInformation {

	private final HttpExchange httpExchange;
	private String requestBody;
	
	protected RequestInformation(HttpExchange httpExchange){
		this.httpExchange = httpExchange;
	}
	
	public Object getRequestAttribute(String attributeName) {
		return httpExchange.getAttribute(attributeName);
	}
	
	public Headers getRequestHeaders() {
		return httpExchange.getRequestHeaders();
	}
	
	public String getRequestMethod() {
		return httpExchange.getRequestMethod();
	}
	
	public URI getRequestURI() {
		return httpExchange.getRequestURI();
	}
	
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
