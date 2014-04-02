package com.disruption.arcilla.httphandler;

public class HttpResponse {

	private final static int HTTP_OK_CODE = 200;
	private final static String EMPTY_BODY = "";

	private final int responseCode;
	private final String responseBody;

	public HttpResponse() {
		this(HTTP_OK_CODE, EMPTY_BODY);
	}

	public HttpResponse(int responseCode) {
		this(responseCode, EMPTY_BODY);
	}

	public HttpResponse(String responseBody) {
		this(HTTP_OK_CODE, EMPTY_BODY);
	}

	public HttpResponse(int responseCode, String responseBody) {
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
