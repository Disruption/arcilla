package com.disruption.arcilla.httphandler;

public interface RequestHandler {
	public HttpResponse processRequest(RequestInformation requestInformation);
}
