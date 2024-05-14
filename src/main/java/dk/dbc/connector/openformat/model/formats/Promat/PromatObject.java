package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonProperty;
import dk.dbc.connector.openformat.model.OpenFormatResponseObject;

import java.util.List;

public class PromatObject implements OpenFormatResponseObject {


    @JsonProperty("promat")
    private List<PromatDisplay> display;


    public List<PromatDisplay> getDisplay() {
        return display;
    }

    public PromatObject setDisplay(List<PromatDisplay> display) {
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
