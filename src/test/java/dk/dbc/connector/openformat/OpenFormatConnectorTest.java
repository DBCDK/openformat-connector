package dk.dbc.connector.openformat;

import com.github.tomakehurst.wiremock.WireMockServer;
import dk.dbc.connector.openformat.model.formats.Promat.PromatElements;
import dk.dbc.connector.openformat.model.formats.Promat.PromatResponse;
import dk.dbc.httpclient.HttpClient;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.client.Client;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class OpenFormatConnectorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenFormatConnectorTest.class);
    private static WireMockServer wireMockServer;
    private static String wireMockHost;
    public static OpenFormatConnector connector;

    final static Client CLIENT = HttpClient.newClient(new ClientConfig()
            .register(new JacksonFeature()));

    @BeforeAll
    static void startWireMockServer() {
//        wireMockServer = new WireMockServer(options().dynamicPort()
//                .dynamicHttpsPort());
//        wireMockServer.start();
//        wireMockHost = "http://localhost:" + wireMockServer.port() + "/server.php";
//        configureFor("localhost", wireMockServer.port());
        //wireMockHost = "http://172.17.33.64:8080";
        //wireMockHost = "http://openformat-php-master.frontend-prod.svc.cloud.dbc.dk/server.php";
        wireMockHost = "http://open-format-broker.cisterne.svc.cloud.dbc.dk/api/v2";
    }

    @BeforeAll
    static void setConnector() {
        connector = new OpenFormatConnector(CLIENT, wireMockHost);
    }

    @AfterAll
    static void stopWireMockServer() {
        //wireMockServer.stop();
    }

    @Test
    public void testOpenFormatPromatFormatResponseWithError() throws OpenFormatConnectorException {

        PromatResponse response = connector.format("B117", PromatResponse.class);
        assertThat("Has 1 object", response.getObjects().size(), is(1));
        assertThat("Has 1 display", response.getObjects().get(0).getDisplay().size(), is(1));
        assertThat("Has an error", response.getObjects().get(0).getDisplay().get(0).getError(), is(notNullValue()));
        assertThat("Has no formatted", response.getObjects().get(0).getDisplay().get(0).getFormatted(), is(nullValue()));
        assertThat("Has expected message", response.getObjects().get(0).getDisplay().get(0).getError(), containsString("Cannot find object: 870970-basis:B117"));
    }

    private void assertResponse(PromatResponse response) {
        assertThat("Has object", response.getObjects(), is(notNullValue()));
        assertThat("Has 1 object", response.getObjects().size(), is(1));

        assertThat("Has display in object", response.getObjects().get(0).getDisplay(), is(notNullValue()));
        assertThat("Has 1 display in object", response.getObjects().get(0).getDisplay().size(), is(1));

        assertThat("Has no error in display", response.getObjects().get(0).getDisplay().get(0).getError(), is(nullValue()));

        assertThat("Has formatted data in display", response.getObjects().get(0).getDisplay().get(0).getFormatted(), is(notNullValue()));

        assertThat("Has record in formatted data", response.getObjects().get(0).getDisplay().get(0).getFormatted().getRecords(), is(notNullValue()));
        assertThat("Has 1 record in formatted data", response.getObjects().get(0).getDisplay().get(0).getFormatted().getRecords().size(), is(1));

        assertThat("Has elements in record", response.getObjects().get(0).getDisplay().get(0).getFormatted().getRecords().get(0).getElements(), is(notNullValue()));
    }

    @Test
    public void testOpenFormatPromatFormatResponseHelpers() throws OpenFormatConnectorException {
        PromatResponse response = connector.format("24699773", PromatResponse.class);

        assertThat("getElementsWithFaust", response.getElementsWithFaust("24699773"), is(notNullValue()));
        assertThat("getElementsWithFaust returns correct element", response.getElementsWithFaust("24699773")
                .getFaust().contains("24699773"), is(true));

        assertThat("getElementsWithIsbn", response.getElementsWithIsbn("9788764432589"), is(notNullValue()));
        assertThat("getElementsWithIsbn returns correct element", response.getElementsWithIsbn("9788764432589")
                .getIsbn().contains("9788764432589"), is(true));
    }

    @Test
    public void testOpenFormatPromatFormatResponse() throws OpenFormatConnectorException {

        PromatResponse response = connector.format("24699773", PromatResponse.class);
        assertResponse(response);
        PromatElements elements = response.getElementsWithFaust("24699773");

        assertThat("has faust", elements.getFaust(), is(notNullValue()));
        assertThat("has 1 faust", elements.getFaust().size(), is(1));
        assertThat("faust", elements.getFaust().get(0), is("24699773"));

        assertThat("has isbn", elements.getIsbn(), is(notNullValue()));
        assertThat("has 1 isbn", elements.getIsbn().size(), is(1));
        assertThat("isbn", elements.getIsbn().get(0), is("9788764432589"));

        /*assertThat("creator", formatResponse.getPromat().get(0).getElements().getCreator().size(), is(1));
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
                .map(OpenFormatValue::getValue)
                .sorted()
                .collect(Collectors.toList())
                .equals(expectedCodes));*/
    }

    /*@Test
    public void testOpenfFormatPromatResponseWithMissingFields() throws OpenFormatConnectorException {

        PromatEntity entity = connector.format("23319322", PromatEntity.class);
        PromatFormatResponse formatResponse = entity.getFormatResponse();
        assertThat(formatResponse.getError().size(), is(0));
        assertThat(formatResponse.getPromat().size(), is(1));

        assertThat("catalogcodes is not null", formatResponse.getPromat().get(0).getElements().getCatalogcodes(), is(notNullValue()));
        assertThat("catalogcodes is empty", formatResponse.getPromat().get(0).getElements().getCatalogcodes().getCode().size(), is(0));
        assertThat("targetGroup is empty", formatResponse.getPromat().get(0).getElements().getTargetgroup().size(), is(0));
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
        assertThat("publisher has 1 string", formatResponse.getPromat().get(0).getElements().getPublisher().size(), is(1));
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
    }*/
}
