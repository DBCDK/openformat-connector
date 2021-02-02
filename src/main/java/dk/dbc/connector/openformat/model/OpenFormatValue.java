package dk.dbc.connector.openformat.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenFormatValue {

    private String value = "";

    @JsonAlias("$")
    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public OpenFormatValue withValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return value;
    }
}
