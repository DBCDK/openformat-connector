/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat;

import dk.dbc.connector.openformat.model.OpenFormatEntity;
import dk.dbc.connector.openformat.model.formats.Promat.PromatEntity;
import dk.dbc.httpclient.FailSafeHttpClient;
import dk.dbc.httpclient.HttpGet;
import dk.dbc.invariant.InvariantUtil;

import net.jodah.failsafe.RetryPolicy;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import dk.dbc.util.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenFormatConnector - openformat client
 * <p>
 * To use this class, you construct an instance, specifying a web resources client as well as
 * a base URL for the openformat service endpoint you will be communicating with.
 * </p>
 * <p>
 * This class is thread safe, as long as the given web resources client remains thread safe.
 * </p>
 */
public class OpenFormatConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenFormatConnector.class);

    private static final String DEFAULT_AGENCY = "870970";

    private static final RetryPolicy<Response> RETRY_POLICY = new RetryPolicy<Response>()
            .handle(ProcessingException.class)
            .handleResultIf(response ->
                    response.getStatus() == 404
                            || response.getStatus() == 500)
            .withDelay(Duration.ofSeconds(5))
            .withMaxRetries(3);

    private final FailSafeHttpClient failSafeHttpClient;
    private final String baseUrl;

    public OpenFormatConnector(Client httpClient, String baseUrl) {
        this(FailSafeHttpClient.create(httpClient, RETRY_POLICY), baseUrl);
    }

    /**
     * Returns new instance with custom retry policy
     *
     * @param failSafeHttpClient web resources client with custom retry policy
     * @param baseUrl            base URL for record service endpoint
     */
    public OpenFormatConnector(FailSafeHttpClient failSafeHttpClient, String baseUrl) {
        this.failSafeHttpClient = InvariantUtil.checkNotNullOrThrow(
                failSafeHttpClient, "failSafeHttpClient");
        this.baseUrl = InvariantUtil.checkNotNullNotEmptyOrThrow(
                baseUrl, "baseUrl");
    }

    public <T extends OpenFormatEntity> T format(String faust, Class c) throws OpenFormatConnectorException {
        return format(faust, DEFAULT_AGENCY, c);
    }

    public <T extends OpenFormatEntity> T  format(String faust, String agency, Class c) throws OpenFormatConnectorException {
        final Stopwatch stopwatch = new Stopwatch();
        LOGGER.info("OpenFormat request with faust {} and agency {}", faust, agency);

        final HttpGet httpGet = new HttpGet(failSafeHttpClient)
                .withBaseUrl(baseUrl)
                .withQueryParameter("action", "formatObject")
                .withQueryParameter("outputFormat", "promat")
                .withQueryParameter("outputType", "json")
                .withQueryParameter("pid", String.format("%s-basis:%s", agency, faust));


        T entity = sendGetRequest(httpGet, c);
        LOGGER.info("Request took {} ms", stopwatch.getElapsedTime(TimeUnit.MILLISECONDS));
        return entity;
    }

    public void close() {
        failSafeHttpClient.getClient().close();
    }

    private <T extends OpenFormatEntity> T sendGetRequest(HttpGet httpGet, Class c) throws OpenFormatConnectorException {
        LOGGER.info("OpenFormat query: {}", httpGet.toString());

        final Response response = httpGet.execute();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new OpenFormatConnectorException(String.format("OpenFormat returned with unexpected status code: %s",
                    response.getStatus()));
        }

        return readResponseEntity(response, c);
    }

    private <T extends OpenFormatEntity> T  readResponseEntity(Response response, Class c) throws OpenFormatConnectorException {
        final T entity = (T) response.readEntity(c);
        if (entity == null) {
            throw new OpenFormatConnectorException("OpenFormat returned with null-valued %s entity");
        }
        return entity;
    }
}