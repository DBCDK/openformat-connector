package dk.dbc.connector.openformat.model;

public class OpenFormatRequestFormat {
    private String name;

    private final String mediaType = "application/json";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpenFormatRequestFormat withFormat(String format) {
        this.name = format;
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    @Override
    public String toString() {
        return "OpenFormatRequestFormat{" +
                "format='" + name + '\'' +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}
