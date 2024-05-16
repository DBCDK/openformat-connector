package dk.dbc.connector.openformat;

import com.github.tomakehurst.wiremock.WireMockServer;
import dk.dbc.connector.openformat.model.OpenFormatElements;
import dk.dbc.connector.openformat.model.formats.Promat.PromatElements;
import dk.dbc.connector.openformat.model.OpenFormatResponse;
import dk.dbc.httpclient.HttpClient;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.client.Client;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

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

        OpenFormatResponse<PromatElements> response = connector.format("B117", PromatElements.class);
        assertThat("Has 1 object", response.getObjects().size(), is(1));
        assertThat("Has 1 display", response.getObjects().get(0).getDisplays().size(), is(1));
        assertThat("Has 1 elements object", response.getObjects().get(0).getDisplays().get((new PromatElements() {}).getName()).size(), is(1));
        assertThat("Has an error", response.getObjects().get(0).getDisplays().get((new PromatElements() {}).getName()).get(0).getError(), is(notNullValue()));
        assertThat("Has no formatted", response.getObjects().get(0).getDisplays().get((new PromatElements() {}).getName()).get(0).getFormatted(), is(nullValue()));
        assertThat("Has expected message", response.getObjects().get(0).getDisplays().get((new PromatElements() {}).getName()).get(0).getError(), containsString("Cannot find object: 870970-basis:B117"));
    }

    @Test
    public void testOpenFormatPromatFormatResponseErrorHelper() throws OpenFormatConnectorException {
        OpenFormatResponse<PromatElements> response = connector.format("B117", PromatElements.class);
        assertThat("has 1 error", response.getErrors().size(), is(1));
        assertThat("has errors", response.hasError(), is(true));
        assertThat("has error", response.getError(), is(notNullValue()));
        assertThat("has expected error", response.getError(), is("Cannot find object: 870970-basis:B117"));
    }

    @Test
    public void testOpenFormatPromatFormatResponseElementsHelper() throws OpenFormatConnectorException {
        OpenFormatResponse<PromatElements> response = connector.format("24699773", PromatElements.class);

        PromatElements elements = response.getElements();
        assertThat("getElements returns correct element", elements.getFaust().contains("24699773"), is(true));
    }

    @Test
    public void testOpenFormatPromatFormatResponseElementsHelperOnError() throws OpenFormatConnectorException {
        OpenFormatResponse<PromatElements> response = connector.format("B117", PromatElements.class);

        PromatElements elements = response.getElements();
        assertThat("getElements is null", elements, is(nullValue()));
    }

    private <T extends OpenFormatElements> T fetchAndAssertResponse(String faust, Class<T> c) throws OpenFormatConnectorException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String formatName = OpenFormatConnector.getFormatName(c);
        OpenFormatResponse<T> response = connector.format(faust, c);

        // Verify that we got a good response
        assertThat(response.hasError(), is(false));

        assertThat("Has object", response.getObjects(), is(notNullValue()));
        assertThat("Has 1 object", response.getObjects().size(), is(1));

        assertThat("Has display in object", response.getObjects().get(0).getDisplays(), is(notNullValue()));
        assertThat("Has 1 display in object", response.getObjects().get(0).getDisplays().size(), is(1));
        assertThat("Has 1 display for format", response.getObjects().get(0).getDisplay(formatName).size(), is(1));

        assertThat("Has no error in display", response.getObjects().get(0).getDisplay(formatName).get(0).getError(), is(nullValue()));
        assertThat("Has no error in display", response.getObjects().get(0).getDisplay(formatName).get(0).getError(), is(nullValue()));

        assertThat("Has formatted data in display", response.getObjects().get(0).getDisplay(formatName).get(0).getFormatted(), is(notNullValue()));

        assertThat("Has record in formatted data", response.getObjects().get(0).getDisplay(formatName).get(0).getFormatted().getRecords(), is(notNullValue()));
        assertThat("Has 1 record in formatted data", response.getObjects().get(0).getDisplay(formatName).get(0).getFormatted().getRecords().size(), is(1));

        assertThat("Has elements in record", response.getObjects().get(0).getDisplay(formatName).get(0).getFormatted().getRecords().get(0).getElements(), is(notNullValue()));

        // Fetch and return the elements object
        T elements = response.getElements();
        assertThat("has element", elements, is(notNullValue()));
        return elements;
    }

    @Test
    public void testOpenFormatPromatFormatResponseFaustHelpers() throws OpenFormatConnectorException {
        OpenFormatResponse<PromatElements> response = connector.format("24699773", PromatElements.class);

        List<PromatElements> elementsList = response.getElementsWithFaust("24699773");
        assertThat("getElementsWithFaust", elementsList, is(notNullValue()));
        assertThat("has 1 element", elementsList.size(), is(1));
        assertThat("getElementsWithFaust returns correct element", elementsList.get(0).getFaust().contains("24699773"), is(true));

        PromatElements elements = response.getElementWithFaust("24699773");
        assertThat("getElementWithFaust", elements, is(notNullValue()));
        assertThat("getElementWithFaust returns correct element", elements.getFaust().contains("24699773"), is(true));
    }

    @Test
    public void testOpenFormatPromatFormatResponseIsbnHelpers() throws OpenFormatConnectorException {
        OpenFormatResponse<PromatElements> response = connector.format("24699773", PromatElements.class);

        List<PromatElements> elementsList = response.getElementsWithIsbn("9788764432589");
        assertThat("getElementsWithIsbn", elementsList, is(notNullValue()));
        assertThat("has 1 element", elementsList.size(), is(1));
        assertThat("getElementsWithIsbn returns correct element", elementsList.get(0).getIsbn().contains("9788764432589"), is(true));

        PromatElements elements = response.getElementWithIsbn("9788764432589");
        assertThat("getElementWithIsbn", elements, is(notNullValue()));
        assertThat("getElementWithIsbn returns correct element", elements.getIsbn().contains("9788764432589"), is(true));
    }

    @Test
    public void testOpenFormatPromatFormatResponse() throws OpenFormatConnectorException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PromatElements elements = fetchAndAssertResponse("24699773", PromatElements.class);

        assertThat("has faust", elements.getFaust(), is(notNullValue()));
        assertThat("has 1 faust", elements.getFaust().size(), is(1));
        assertThat("faust", elements.getFaust().get(0), is("24699773"));

        assertThat("has isbn", elements.getIsbn(), is(notNullValue()));
        assertThat("has 1 isbn", elements.getIsbn().size(), is(1));
        assertThat("isbn", elements.getIsbn().get(0), is("9788764432589"));

        assertThat("creator", elements.getCreator().size(), is(1));
        assertThat("creator", elements.getCreator().get(0), is("Kꜳrsbøl, Jette A."));
        assertThat("dk5", elements.getDk5().size(), is(1));
        assertThat("dk5", elements.getDk5().get(0), is("sk"));
        assertThat("title", elements.getTitle().size(), is(1));
        assertThat("title", elements.getTitle().get(0), is("Den lukkede bog"));
        assertThat("targetGroup", elements.getTargetGroup().size(), is(1));
        assertThat("targetGroup", elements.getTargetGroup().get(0), is("v"));
        assertThat("extent", elements.getExtent().size(), is(1));
        assertThat("extent", elements.getExtent().get(0), is("21 t., 29 min."));
        assertThat("publisher", elements.getPublisher().size(), is(1));
        assertThat("publisher", elements.getPublisher().get(0), is("Ballerup, Biblioteksmedier, 2011"));

        assertThat("materialtypes", elements.getMaterialTypes(), is(notNullValue()));
        assertThat("materialtypes", elements.getMaterialTypes().getType(), is(notNullValue()));
        assertThat("materialtypes", elements.getMaterialTypes().getType().size(), is(1));
        assertThat("materialtypes", elements.getMaterialTypes().getType().get(0), is("Lydbog (cd)"));

        assertThat("catalogcodes", elements.getCatalogCodes(), is(notNullValue()));
        assertThat("catalogcodes", elements.getCatalogCodes().getCode(), is(notNullValue()));
        assertThat("catalogcodes", elements.getCatalogCodes().getCode().size(), is(3));
        List<String> expectedCodes = List.of("BKM201105", "DAT201827", "DLF201105");
        assertThat("catalogcodes", elements.getCatalogCodes().getCode()
                .stream()
                .sorted()
                .collect(Collectors.toList())
                .equals(expectedCodes));
    }

    @Test
    public void testOpenFormatPromatResponseWithMissingFields() throws OpenFormatConnectorException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PromatElements elements = fetchAndAssertResponse("23319322", PromatElements.class);

        assertThat("catalogcodes is null", elements.getCatalogCodes(), is(nullValue()));
        assertThat("targetGroup is empty", elements.getTargetGroup().size(), is(0));
    }

    @Test
    public void testOpenfFormatPromatResponseWithPublisher() throws OpenFormatConnectorException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PromatElements elements = fetchAndAssertResponse("38600052", PromatElements.class);

        assertThat("publisher is not null", elements.getPublisher(), is(notNullValue()));
        assertThat("publisher has 1 string", elements.getPublisher().size(), is(1));
        assertThat("publisher has expected value", elements.getPublisher().get(0), is("Kbh., Faraos Cigarer, Fahrenheit, 2020 i.e. 2021"));
    }

    @Test
    public void testOpenfFormatPromatResponseWithMetakompasSubject() throws OpenFormatConnectorException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PromatElements elements = fetchAndAssertResponse("38600052", PromatElements.class);

        assertThat("metakompassubject", elements.getMetakompasSubject(), is(notNullValue()));
        assertThat("metakompassubject", elements.getMetakompasSubject().get(0), is("true"));
    }

    @Test
    public void testOpenfFormatPromatResponseWithDk5IsArray() throws OpenFormatConnectorException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PromatElements elements = fetchAndAssertResponse("26485878", PromatElements.class);

        assertThat("dk5", elements.getDk5(), is(notNullValue()));
        assertThat("dk5 size", elements.getDk5().size(), is(2));
        assertThat("dk5 0", elements.getDk5().get(0), is("79.864"));
        assertThat("dk5 1", elements.getDk5().get(1).trim(), is("Olsen, Ole")); // Todo: There is currently an error in OpenFormat that puts a leading blank in front of " Olsen". When that is fixed, we can remove the 'trim()'
    }

    @Test
    public void testOpenFormatPromatResponseWithTargetGroupAsArray() throws OpenFormatConnectorException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PromatElements elements = fetchAndAssertResponse("38743929", PromatElements.class);

        assertThat("targetGroup is list of 2", elements.getTargetGroup().size(), is(2));
    }
}
