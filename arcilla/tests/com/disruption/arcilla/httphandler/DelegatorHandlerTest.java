package com.disruption.arcilla.httphandler;

import com.sun.net.httpserver.HttpExchange;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DelegatorHandlerTest {

    @Mock
    private RequestHandler requestHandler;

    @Mock
    private HttpExchange httpExchange;

    @Mock
    private OutputStream outputStream;

    private final HttpResponse httpResponse = new HttpResponse();

    // Tested instance
    private DelegatorHandler delegatorHandler;

    @Before
    public void setUp(){
        when(requestHandler.processRequest(Mockito.any(RequestInformation.class))).thenReturn(httpResponse);
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        delegatorHandler = new DelegatorHandler(requestHandler);
    }

    @Test
    public void triggersRequestHandlerProcessRequestCallOnHandle() throws IOException{
        // When
        delegatorHandler.handle(httpExchange);

        // Then
        verify(requestHandler).processRequest(Mockito.any(RequestInformation.class));
    }

    @Test
    public void sendsResponseHeadersOnHandle() throws IOException{
        // When
        delegatorHandler.handle(httpExchange);

        // Then
        verify(httpExchange).sendResponseHeaders(httpResponse.getResponseCode(), httpResponse.getResponseBody().length());
    }

    @Test
    public void writesResponseToOutputStreamOnHandle() throws IOException{
        // When
        delegatorHandler.handle(httpExchange);

        // Then
        verify(outputStream).write(httpResponse.getResponseBody().getBytes());
    }

    @Test
    public void closesHttpExchangeOnHandle() throws IOException{
        // When
        delegatorHandler.handle(httpExchange);

        // Then
        verify(httpExchange).close();
    }
}
