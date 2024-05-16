package dk.dbc.connector.openformat;

import java.lang.reflect.InvocationTargetException;

public class OpenFormatConnectorTestWireMockRecorder {
        /*
        Steps to reproduce wiremock recording:

        * Start standalone runner
            java -jar wiremock-standalone-{WIRE_MOCK_VERSION}.jar --proxy-all="{OpenFormat_SERVICE_HOST}" --record-mappings --verbose

        * Run the main method of this class
            mvn exec:java -Dexec.mainClass="dk.dbc.openformat.OpenFormatConnectorTestWireMockRecorder"

        * Replace content of src/test/resources/{__files|mappings} with that produced by the standalone runner
     */
    public static void main(String[] args) throws Exception {
        OpenFormatConnectorTest.connector = new OpenFormatConnector(
                OpenFormatConnectorTest.CLIENT, "http://localhost:8080");
        final OpenFormatConnectorTest OpenFormatConnectorTest = new OpenFormatConnectorTest();
        recordGetApplicantRequests(OpenFormatConnectorTest);
    }

    private static void recordGetApplicantRequests(OpenFormatConnectorTest OpenFormatConnectorTest)
            throws OpenFormatConnectorException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        OpenFormatConnectorTest.testOpenFormatPromatFormatResponse();
        OpenFormatConnectorTest.testOpenFormatPromatFormatResponseWithError();
    }

}
