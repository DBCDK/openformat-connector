/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dk.dbc.connector.openformat.model.OpenFormatDeserializer;
import dk.dbc.connector.openformat.model.OpenFormatValue;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromatCatalogCodes {

    private List<OpenFormatValue> code = new ArrayList<>();

    public List<OpenFormatValue> getCode() {
        return code;
    }

    @JsonDeserialize(using = OpenFormatDeserializer.class)
    public void setCode(List<OpenFormatValue> code) {
        this.code = code;
    }

    public PromatCatalogCodes withCode(List<OpenFormatValue> code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "PromatCatalogCodes{" +
                "code=" + code +
                '}';
    }
}
