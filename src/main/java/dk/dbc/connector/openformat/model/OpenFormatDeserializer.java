/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OpenFormatDeserializer extends JsonDeserializer<List<OpenFormatValue>> {

    @Override
    public List<OpenFormatValue> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<OpenFormatValue> values;

        if(JsonToken.START_OBJECT.equals(jp.getCurrentToken())) {
            values = Arrays.asList((new OpenFormatValue[] { mapper.readValue(jp, OpenFormatValue.class) }));
        } else if(JsonToken.START_ARRAY.equals(jp.getCurrentToken())) {
            values = Arrays.asList(mapper.readValue(jp, OpenFormatValue[].class));
        }
        else {
            throw new OpenFormatDeserializationException("Unexpected token received when either OpenFormatValue or OpenFormatValue[] was expected.");
        }
        return values;
    }

}
