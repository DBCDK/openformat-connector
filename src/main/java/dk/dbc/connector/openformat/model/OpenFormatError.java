/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenFormatError {

    OpenFormatValue message;

    public OpenFormatValue getMessage() {
        return message;
    }

    public void setMessage(OpenFormatValue message) {
        this.message = message;
    }

    public OpenFormatError withMessage(OpenFormatValue message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "OpenFormatError{" +
                "message=" + message +
                '}';
    }
}
