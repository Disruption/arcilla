package com.disruption.arcilla.httphandler;

/**
 * Class in charge of generating the response code and response String body for a given
 * request using the info provided by the RequestInformation
 *
 * @author Disruption <stomasortiz@gmail.com>
 */
public interface RequestHandler {
    /**
     * Processes the request using the information provided to generate a correct HttpResponse
     * @param requestInformation Information to process
     * @return HttpResponse with the request results
     */
	public HttpResponse processRequest(RequestInformation requestInformation);
}
