package dk.dbc.connector.openformat.model;

public class OpenFormatRecord<T extends OpenFormatElements> {
    private T elements;

    public T getElements() {
        return elements;
    }

    public void setElements(T elements) {
        this.elements = elements;
    }

    public OpenFormatRecord<T> withElements(T elements) {
        this.elements = elements;
        return this;
    }

    @Override
    public String toString() {
        return "OpenFormatRecord{" +
                "elements=" + elements +
                '}';
    }
}
