/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromatFormat {

    private PromatElements elements;

    public PromatElements getElements() {
        return elements;
    }

    public void setElements(PromatElements elements) {
        this.elements = elements;
    }

    public PromatFormat withElements(PromatElements elements) {
        this.elements = elements;
        return this;
    }

    @Override
    public String toString() {
        return "Promat{" +
                "elements=" + elements +
                '}';
    }
}
