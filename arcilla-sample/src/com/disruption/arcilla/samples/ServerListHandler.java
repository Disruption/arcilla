package com.disruption.arcilla.samples;

import com.disruption.arcilla.httphandler.HttpResponse;
import com.disruption.arcilla.httphandler.RequestHandler;
import com.disruption.arcilla.httphandler.RequestInformation;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ServerListHandler implements RequestHandler {
    public HttpResponse processRequest(RequestInformation requestInformation) {
        // Populate the list
        List<String> serverListUrls = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            serverListUrls.add("Url for server " + i);
        }

        // For example, encode the list as Json to generate the response body string
        String responseBody = new Gson().toJson(serverListUrls);

        return new HttpResponse(HttpResponse.HTTP_OK_CODE, responseBody);
    }
}
