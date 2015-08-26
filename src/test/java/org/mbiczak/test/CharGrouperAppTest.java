package org.mbiczak.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mbiczak.CharGrouperApp;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * Tests CharGrouperApp application. Test is based on a simple GET call to the CharGrouperController (/group).
 */
public class CharGrouperAppTest  {
    private final static String ENDPOINT_URL = "http://localhost:8080/group";
    private final static String INPUT = "abzuaaissna";
    private final static String EXPECTED_RESULT = "a4bins2uz";

    private ConfigurableApplicationContext context;

    /**
     * Setup application context.
     */
    @Before
    public void startApp() {
        context = SpringApplication.run(CharGrouperApp.class);
    }

    /**
     * Close application context.
     */
    @After
    public void closeApp() {
        context.close();
    }

    /**
     * Perform simple GET test. Test should invoke CharGrouperController.
     *
     * @throws IOException
     */
    @Test
    public void testService() throws IOException {
        // given
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(ENDPOINT_URL + "?chars=" + INPUT);

        // then
        CloseableHttpResponse httpResponse = httpClient.execute(request);

        // then
        assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
        assertEquals(EXPECTED_RESULT, EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
    }
}
