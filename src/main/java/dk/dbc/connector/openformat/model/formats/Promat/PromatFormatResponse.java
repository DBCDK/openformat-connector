/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.dbc.connector.openformat.model.OpenFormatError;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromatFormatResponse {

    private List<PromatFormat> promat;

    private List<OpenFormatError> error = new ArrayList<>();

    public List<OpenFormatError> getError() {
        return error;
    }

    public void setError(List<OpenFormatError> error) {
        this.error = error;
    }

    public PromatFormatResponse withError(List<OpenFormatError> error) {
        this.error = error;
        return this;
    }

    public List<PromatFormat> getPromat() {
        return promat;
    }

    public void setPromat(List<PromatFormat> result) {
        this.promat = result;
    }

    public PromatFormatResponse withPromat(List<PromatFormat> result) {
        this.promat = result;
        return this;
    }

    @Override
    public String toString() {
        return "PromatFormatResponse{" +
                "promat=" + promat +
                '}';
    }
}
