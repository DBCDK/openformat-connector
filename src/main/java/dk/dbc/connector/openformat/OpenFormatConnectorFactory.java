package dk.dbc.connector.openformat;

import dk.dbc.httpclient.HttpClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;

/**
 * OpenFormatConnector factory
 * <p>
 * Synopsis:
 * </p>
 * <pre>
 *    // New instance
 *    OpenFormatConnector connector = OpenFormatConnectorFactory.create("http://openformat-service");
 *
 *    // Singleton instance in CDI enabled environment
 *    {@literal @}Inject
 *    OpenFormatConnectorFactory factory;
 *    ...
 *    OpenFormatConnector connector = factory.getInstance();
 *
 *    // or simply
 *    {@literal @}Inject
 *    OpenFormatConnector connector;
 * </pre>
 * <p>
 * The CDI case depends on the OpenFormat service base-url being defined as
 * the value of either a system property or environment variable
 * named OPENFORMAT_SERVICE_URL.
 * </p>
 */
@ApplicationScoped
public class OpenFormatConnectorFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenFormatConnectorFactory.class);

    public static OpenFormatConnector create(String baseUrl) throws OpenFormatConnectorException {
        final Client client = HttpClient.newClient(new ClientConfig()
                .register(new JacksonFeature()));
        LOGGER.info("Creating OpenFormatConnector for: {}", baseUrl);
        return new OpenFormatConnector(client, baseUrl);
    }

    @Inject
    @ConfigProperty(name = "OPENFORMAT_SERVICE_URL", defaultValue = "")
    private String baseUrl;

    OpenFormatConnector openformatConnector;

    @PostConstruct
    public void initializeConnector() {
        try {
            openformatConnector = OpenFormatConnectorFactory.create(baseUrl);
        } catch (OpenFormatConnectorException e) {
            throw new IllegalStateException(e);
        }
    }

    @Produces
    public OpenFormatConnector getInstance() {
        return openformatConnector;
    }

    @PreDestroy
    public void tearDownConnector() {
        openformatConnector.close();
    }
}
