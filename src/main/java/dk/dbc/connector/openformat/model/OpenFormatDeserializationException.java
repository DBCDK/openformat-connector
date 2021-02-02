/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model;

import com.fasterxml.jackson.core.JsonProcessingException;

public class OpenFormatDeserializationException extends JsonProcessingException {

    public OpenFormatDeserializationException(String message) {
        super(message);
    }
}

