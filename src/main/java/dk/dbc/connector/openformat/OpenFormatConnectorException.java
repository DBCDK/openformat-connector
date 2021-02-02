/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat;

public class OpenFormatConnectorException extends Exception {
    public OpenFormatConnectorException(String message) {
        super(message);
    }

    public OpenFormatConnectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
