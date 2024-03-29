package dk.dbc.connector.openformat;

import com.github.tomakehurst.wiremock.WireMockServer;
import dk.dbc.connector.openformat.model.formats.Promat.PromatEntity;
import dk.dbc.connector.openformat.model.formats.Promat.PromatFormatResponse;
import dk.dbc.httpclient.HttpClient;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.client.Client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class OpenFormatConnectorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenFormatConnectorTest.class);
    private static WireMockServer wireMockServer;
    private static String wireMockHost;
    public static OpenFormatConnector connector;

    final static Client CLIENT = HttpClient.newClient(new ClientConfig()
            .register(new JacksonFeature()));

    @BeforeAll
    static void startWireMockServer() {
        wireMockServer = new WireMockServer(options().dynamicPort()
                .dynamicHttpsPort());
        wireMockServer.start();
        wireMockHost = "http://localhost:" + wireMockServer.port() + "/server.php";
        configureFor("localhost", wireMockServer.port());
    }

    @BeforeAll
    static void setConnector() {
        connector = new OpenFormatConnector(CLIENT, wireMockHost);
    }

    @AfterAll
    static void stopWireMockServer() {
        wireMockServer.stop();
    }

    @Test
    public void testOpenFormatPromatFormatResponseWithError() throws OpenFormatConnectorException {

        try {
            PromatEntity entity = connector.format("B117", PromatEntity.class);
            PromatFormatResponse formatResponse = entity.getFormatResponse();
            assertThat(formatResponse.getError().size(), is(1));
            assertThat(formatResponse.getError().get(0).getMessage()
                    .getValue().isEmpty(), is(false));
            assertThat(formatResponse.getError().get(0).getMessage()
                    .getValue().contains("Could not load display"), is(true));
        }
        catch(OpenFormatConnectorException connectorException) {
            throw connectorException;
        }
    }

    @Test
    public void testOpenFormatPromatFormatResponse() throws OpenFormatConnectorException {

        try {
            PromatEntity entity = connector.format("24699773", PromatEntity.class);
            PromatFormatResponse formatResponse = entity.getFormatResponse();
            assertThat(formatResponse.getError().size(), is(0));

            assertThat("faust", formatResponse.getPromat().get(0).getElements().getFaust().toString(), is("24699773"));
            assertThat("creator", formatResponse.getPromat().get(0).getElements().getCreator().size(), is(1));
            assertThat("creator", formatResponse.getPromat().get(0).getElements().getCreator().get(0).toString(), is("Kꜳrsbøl, Jette A."));
            assertThat("dk5", formatResponse.getPromat().get(0).getElements().getDk5().get(0).toString(), is("sk"));
            assertThat("title", formatResponse.getPromat().get(0).getElements().getTitle().toString(), is("Den lukkede bog"));
            assertThat("targetgroup", formatResponse.getPromat().get(0).getElements().getTargetgroup().toString(), is("[v]"));
            assertThat("extent", formatResponse.getPromat().get(0).getElements().getExtent().toString(), is("21 t., 29 min."));
            assertThat("publisher", formatResponse.getPromat().get(0).getElements().getPublisher().size(), is(1));
            assertThat("publisher", formatResponse.getPromat().get(0).getElements().getPublisher().get(0).toString(), is("Ballerup, Biblioteksmedier, 2011"));

            assertThat("isbn", formatResponse.getPromat().get(0).getElements().getIsbn()
                    .size(), is(1));
            assertThat("isbn", formatResponse.getPromat().get(0).getElements().getIsbn()
                    .get(0).toString(), is("9788764432589"));

            assertThat("materialtypes", formatResponse.getPromat().get(0).getElements().getMaterialtypes().getType()
                    .size(), is(1));
            assertThat("materialtypes", formatResponse.getPromat().get(0).getElements().getMaterialtypes().getType()
                    .get(0).toString(), is("Lydbog (cd)"));

            assertThat("catalogcodes", formatResponse.getPromat().get(0).getElements().getCatalogcodes().getCode()
                    .size(), is(3));
            List<String> expectedCodes = Arrays.asList("BKM201105", "DAT201827", "DLF201105");
            assertThat("catalogcodes", formatResponse.getPromat().get(0).getElements().getCatalogcodes().getCode()
                    .stream()
                    .map(code -> code.getValue())
                    .sorted()
                    .collect(Collectors.toList())
                    .equals(expectedCodes));
        }
        catch(OpenFormatConnectorException connectorException) {
            throw connectorException;
        }
    }

    @Test
    public void testOpenfFormatPromatResponseWithMissingFields() throws OpenFormatConnectorException {

        PromatEntity entity = connector.format("23319322", PromatEntity.class);
        PromatFormatResponse formatResponse = entity.getFormatResponse();
        assertThat(formatResponse.getError().size(), is(0));
        assertThat(formatResponse.getPromat().size(), is(1));

        assertThat("catalogcodes is not null", formatResponse.getPromat().get(0).getElements().getCatalogcodes(), is(notNullValue()));
        assertThat("catalogcodes is empty", formatResponse.getPromat().get(0).getElements().getCatalogcodes().getCode().size(), is(0));

        assertThat("targetGroup is 'v'", formatResponse.getPromat().get(0).getElements().getTargetgroup().get(0).toString(), is("v"));
    }

    @Test
    public void testOpenfFormatPromatResponsePublisherIsArray() throws OpenFormatConnectorException {

        PromatEntity entity = connector.format("38600052", PromatEntity.class);
        PromatFormatResponse formatResponse = entity.getFormatResponse();
        assertThat(formatResponse.getError().size(), is(0));
        assertThat(formatResponse.getPromat().size(), is(1));

        assertThat("catalogcodes is not null", formatResponse.getPromat().get(0).getElements().getCatalogcodes(), is(notNullValue()));
        assertThat("catalogcodes is empty", formatResponse.getPromat().get(0).getElements().getCatalogcodes().getCode().size(), is(3));
        assertThat("targetGroup is not null", formatResponse.getPromat().get(0).getElements().getTargetgroup().get(0).getValue(), is(notNullValue()));
        assertThat("title is not null", formatResponse.getPromat().get(0).getElements().getTitle().getValue(), is(notNullValue()));
        assertThat("publisher is not null", formatResponse.getPromat().get(0).getElements().getPublisher(), is(notNullValue()));
        assertThat("publisher has 2 strings", formatResponse.getPromat().get(0).getElements().getPublisher().size(), is(2));
    }

    @Test
    public void testOpenfFormatPromatResponseMetakompasSubject() throws OpenFormatConnectorException {

        PromatEntity entity = connector.format("38600052", PromatEntity.class);
        PromatFormatResponse formatResponse = entity.getFormatResponse();
        assertThat(formatResponse.getError().size(), is(0));
        assertThat(formatResponse.getPromat().size(), is(1));

        assertThat("metakompassubject", formatResponse.getPromat().get(0).getElements().getMetakompassubject().getValue(), is("true"));
    }

    @Test
    public void testOpenfFormatPromatResponseWithDk5IsArray() throws OpenFormatConnectorException {

        PromatEntity entity = connector.format("26485878", PromatEntity.class);
        PromatFormatResponse formatResponse = entity.getFormatResponse();
        assertThat(formatResponse.getError().size(), is(0));
        assertThat(formatResponse.getPromat().size(), is(1));

        assertThat("dk5", formatResponse.getPromat().get(0).getElements().getDk5(), is(notNullValue()));
        assertThat("dk5 size", formatResponse.getPromat().get(0).getElements().getDk5().size(), is(2));
        assertThat("dk5 0", formatResponse.getPromat().get(0).getElements().getDk5().get(0).getValue(), is("79.864"));
        assertThat("dk5 1", formatResponse.getPromat().get(0).getElements().getDk5().get(1).getValue(), is("Olsen, Ole"));
    }

    @Test
    public void testOpenFormatPromatResponseWithTargetGroupAsArray() throws OpenFormatConnectorException {
        PromatEntity entity = connector.format("38743929", PromatEntity.class);
        PromatFormatResponse formatResponse = entity.getFormatResponse();
        assertThat(formatResponse.getError().size(), is(0));
        assertThat(formatResponse.getPromat().size(), is(1));
        assertThat("targetGroup is list of 2",
                formatResponse.getPromat().get(0).getElements().getTargetgroup().size(),
                is(2));

    }
}
