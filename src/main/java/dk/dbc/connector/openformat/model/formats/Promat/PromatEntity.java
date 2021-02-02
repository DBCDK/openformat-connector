/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.dbc.connector.openformat.model.OpenFormatEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromatEntity implements OpenFormatEntity {

    private PromatFormatResponse formatResponse;

    public PromatFormatResponse getFormatResponse() {
        return formatResponse;
    }

    public void setFormatResponse(PromatFormatResponse formatResponse) {
        this.formatResponse = formatResponse;
    }

    public PromatEntity withFormatResponse(PromatFormatResponse formatResponse) {
        this.formatResponse = formatResponse;
        return this;
    }

    @Override
    public String toString() {
        return "PromatEntity{" +
                "formatResponse=" + formatResponse +
                '}';
    }
}
