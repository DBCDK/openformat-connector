package dk.dbc.connector.openformat.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenFormatObject<T extends OpenFormatElements> {

    private Map<String, List<OpenFormatDisplay<T>>> displays = new HashMap<>();

    @JsonAnySetter
    public void addDisplay(String name, List<OpenFormatDisplay<T>> display) {
        displays.put(name, display);
    }

    public Map<String, List<OpenFormatDisplay<T>>> getDisplays() {
        return this.displays;
    }

    public void setDisplays(Map<String, List<OpenFormatDisplay<T>>> displays) {
        this.displays = displays;
    }

    public OpenFormatObject<T> withDisplays(Map<String, List<OpenFormatDisplay<T>>> displays) {
        this.displays = displays;
        return this;
    }

    public List<OpenFormatDisplay<T>> getDisplay(String name) {
        return displays.containsKey(name) ? displays.get(name) : List.of();
    }

    @Override
    public String toString() {
        return "OpenFormatObject{" +
                "displays=" + displays +
                '}';
    }
}
