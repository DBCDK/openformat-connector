package dk.dbc.connector.openformat.model;

import dk.dbc.connector.openformat.model.formats.Promat.PromatElements;

public class OpenFormatRecord {
    private PromatElements elements;

    public PromatElements getElements() {
        return elements;
    }

    public void setElements(PromatElements elements) {
        this.elements = elements;
    }

    public OpenFormatRecord withElements(PromatElements elements) {
        this.elements = elements;
        return this;
    }

    @Override
    public String toString() {
        return "PromatRecord{" +
                "elements=" + elements +
                '}';
    }
}
