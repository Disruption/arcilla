package com.disruption.arcilla.httphandler;

/**
 * Small POJO class that holds the response code and the string body to return in the
 * HttpExchange output being handled by a DelegatorHandler
 *
 * @author Disruption <stomasortiz@gmail.com>
 */
public class HttpResponse {

	public final static int HTTP_OK_CODE = 200;
    public final static String EMPTY_BODY = "";
    public static final int MINIMUM_RESPONSE_CODE = 100;
    public static final int MAXIMUM_RESPONSE_CODE = 599;

    private final int responseCode;
	private final String responseBody;

	public HttpResponse() {
		this(HTTP_OK_CODE, EMPTY_BODY);
	}

	public HttpResponse(int responseCode) {
		this(responseCode, EMPTY_BODY);
	}

	public HttpResponse(String responseBody) {
		this(HTTP_OK_CODE, responseBody);
	}

	public HttpResponse(int responseCode, String responseBody) {
        if (responseCode < MINIMUM_RESPONSE_CODE || responseCode > MAXIMUM_RESPONSE_CODE){
            throw new IllegalArgumentException("Invalid response code " + responseCode + ". " +
                    "Value should be in the interval [" + MINIMUM_RESPONSE_CODE + " , " + MAXIMUM_RESPONSE_CODE + "]");
        }

        if (responseBody == null) {
            throw new IllegalArgumentException("Response body can not be null, " +
                    "use empty constructor or response code constructor instead");
        }
		this.responseCode = responseCode;
		this.responseBody = responseBody;
	}
	
	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseBody() {
		return responseBody;
	}
}
