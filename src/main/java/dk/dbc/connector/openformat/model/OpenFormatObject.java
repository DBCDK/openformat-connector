package dk.dbc.connector.openformat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenFormatObject implements OpenFormatResponseObject {

    @JsonProperty("promat")
    private List<OpenFormatDisplay> display;

    public List<OpenFormatDisplay> getDisplay() {
        return display;
    }

    public OpenFormatObject setDisplay(List<OpenFormatDisplay> display) {
        this.display = display;
        return this;
    }

    @Override
    public String toString() {
        return "PromatObject{" +
                "display=" + display +
                '}';
    }
}
