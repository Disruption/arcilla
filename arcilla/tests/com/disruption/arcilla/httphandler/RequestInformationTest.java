package com.disruption.arcilla.httphandler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RequestInformationTest {
    private final static String ANY_ATTRIBUTE_NAME = "ATTRIBUTE";
    private final static String ANY_ATTRIBUTE_VALUE = "VALUE";
    private final static String ANY_REQUEST_METHOD = "METHOD";
    private URI anyUri;

    @Mock
    private HttpExchange httpExchange;

    @Mock
    private Headers anyHeaders;

    // Tested instance
    private RequestInformation requestInformation;

    @Before
    public void setUp() throws URISyntaxException {
        anyUri = new URI("");

        when(httpExchange.getAttribute(ANY_ATTRIBUTE_NAME)).thenReturn(ANY_ATTRIBUTE_VALUE);
        when(httpExchange.getRequestHeaders()).thenReturn(anyHeaders);
        when(httpExchange.getRequestMethod()).thenReturn(ANY_REQUEST_METHOD);
        when(httpExchange.getRequestURI()).thenReturn(anyUri);


        requestInformation = new RequestInformation(httpExchange);
    }

    @Test
    public void getRequestAttributeReturnsValueFromWrappedHttpExchange() {
        // When
        Object attributeValue = requestInformation.getRequestAttribute(ANY_ATTRIBUTE_NAME);

        // Then
        assertThat(ANY_ATTRIBUTE_VALUE, is(attributeValue));
    }

    @Test
    public void getRequestHeadersReturnsValueFromWrappedHttpExchange() {
        // When
        Headers headers = requestInformation.getRequestHeaders();

        // Then
        assertThat(headers, is(anyHeaders));
    }

    @Test
    public void getRequestMethodReturnsValueFromWrappedHttpExchange() {
        // When
        String requestMethod = requestInformation.getRequestMethod();

        // Then
        assertThat(requestMethod, is(ANY_REQUEST_METHOD));
    }

    @Test
    public void getRequestURIReturnsValueFromWrappedHttpExchange() {
        // When
        URI uri = requestInformation.getRequestURI();

        // Then
        assertThat(uri, is(anyUri));
    }
}
