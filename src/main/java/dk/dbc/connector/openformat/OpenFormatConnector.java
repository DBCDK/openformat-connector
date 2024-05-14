package dk.dbc.connector.openformat;

import dk.dbc.connector.openformat.model.OpenFormatRequest;
import dk.dbc.connector.openformat.model.OpenFormatResponse;
import dk.dbc.httpclient.FailSafeHttpClient;

import dk.dbc.httpclient.HttpPost;
import jakarta.ws.rs.core.MediaType;
import net.jodah.failsafe.RetryPolicy;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Response;

import java.lang.reflect.InvocationTargetException;
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

    private static final String FORMAT_REQUEST = "format";

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
        if (failSafeHttpClient == null || baseUrl == null) {
            throw new NullPointerException(String.format("Null parameter passed in call to OpenFormatConnector(%s, %s)",
                    failSafeHttpClient == null ? "null" : failSafeHttpClient.toString(), baseUrl == null ? "null" : baseUrl));
        }
        this.failSafeHttpClient = failSafeHttpClient;
        this.baseUrl = baseUrl;
    }

    public <T extends OpenFormatResponse> T format(String faust, Class c) throws OpenFormatConnectorException {
        return format(faust, DEFAULT_AGENCY, c);
    }

    public <T extends OpenFormatResponse> T format(String faust, String agency, Class c) throws OpenFormatConnectorException {
        try {
            final Stopwatch stopwatch = new Stopwatch();
            LOGGER.info("OpenFormat request with faust {} and agency {}", faust, agency);

            final HttpPost httpPost = new HttpPost(failSafeHttpClient)
                    .withBaseUrl(baseUrl)
                    .withPathElements(FORMAT_REQUEST)
                    .withData(new OpenFormatRequest(((T) c.getDeclaredConstructor().newInstance()).getName(), agency, faust), MediaType.APPLICATION_JSON);

            T entity = sendPostRequest(httpPost, c);
            LOGGER.info("Request took {} ms", stopwatch.getElapsedTime(TimeUnit.MILLISECONDS));
            LOGGER.info("Response: {}", entity);
            return entity;
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new OpenFormatConnectorException("Caught exception while trying to send request", e);
        }
    }

    public void close() {
        failSafeHttpClient.getClient().close();
    }

    private <T extends OpenFormatResponse> T sendPostRequest(HttpPost httpPost, Class c) throws OpenFormatConnectorException {
        LOGGER.info("OpenFormat request: {}", httpPost.toString());
        LOGGER.info("With data: {}", httpPost.getEntity().toString());

        final Response response = httpPost.execute();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new OpenFormatConnectorException(String.format("OpenFormat returned with unexpected status code: %s",
                    response.getStatus()));
        }

        return readResponseEntity(response, c);
    }

    private <T extends OpenFormatResponse> T readResponseEntity(Response response, Class c) throws OpenFormatConnectorException {
        final T entity = (T) response.readEntity(c);
        if (entity == null) {
            throw new OpenFormatConnectorException("OpenFormat returned with null-valued %s entity");
        }
        return entity;
    }
}
