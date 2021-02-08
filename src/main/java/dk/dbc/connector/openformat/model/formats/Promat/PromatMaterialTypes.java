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
public class PromatMaterialTypes {

    private List<OpenFormatValue> type = new ArrayList<>();

    public List<OpenFormatValue> getType() {
        return type;
    }

    @JsonDeserialize(using = OpenFormatDeserializer.class)
    public void setType(List<OpenFormatValue> type) {
        this.type = type;
    }

    public PromatMaterialTypes withType(List<OpenFormatValue> type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "PromatMaterialTypes{" +
                "type=" + type +
                '}';
    }
}
