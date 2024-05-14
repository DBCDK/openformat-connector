package dk.dbc.connector.openformat.model.formats.Promat;

public class PromatRecord {
    private PromatElements elements;

    public PromatElements getElements() {
        return elements;
    }

    public void setElements(PromatElements elements) {
        this.elements = elements;
    }

    public PromatRecord withElements(PromatElements elements) {
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
