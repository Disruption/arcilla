package com.disruption.arcilla.httphandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HttpResponseTest {

    private final static String ANY_BODY = "BODY";
    private final static int ANY_RESPONSE_CODE = 301;

    @Test
    public void emptyConstructorStoresDefaultValues() {
        // Given
        HttpResponse httpResponse = new HttpResponse();

        // Then
        assertThat(httpResponse.getResponseCode(), is(HttpResponse.HTTP_OK_CODE));
        assertThat(httpResponse.getResponseBody(), is(HttpResponse.EMPTY_BODY));
    }

    @Test
    public void constructorWithResponseCodeStoresDefaultBodyAndProvidedResponseCode() {
        // Given
        HttpResponse httpResponse = new HttpResponse(ANY_RESPONSE_CODE);

        // Then
        assertThat(httpResponse.getResponseCode(), is(ANY_RESPONSE_CODE));
        assertThat(httpResponse.getResponseBody(), is(HttpResponse.EMPTY_BODY));
    }

    @Test
    public void constructorWithBodyStoresDefaultResponseCodeAndProvidedBody() {
        // Given
        HttpResponse httpResponse = new HttpResponse(ANY_BODY);

        // Then
        assertThat(httpResponse.getResponseCode(), is(HttpResponse.HTTP_OK_CODE));
        assertThat(httpResponse.getResponseBody(), is(ANY_BODY));
    }

    @Test
    public void fullConstructorStoresProvidedValues() {
        // Given
        HttpResponse httpResponse = new HttpResponse(ANY_RESPONSE_CODE, ANY_BODY);

        // Then
        assertThat(httpResponse.getResponseCode(), is(ANY_RESPONSE_CODE));
        assertThat(httpResponse.getResponseBody(), is(ANY_BODY));
    }
}
