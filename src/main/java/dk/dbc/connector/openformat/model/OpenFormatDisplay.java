package dk.dbc.connector.openformat.model;

public class OpenFormatDisplay<T extends OpenFormatElements> {
    private String mediaType;

    private String error;

    private OpenFormatFormatted<T> formatted;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public OpenFormatDisplay<T> withMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public OpenFormatDisplay<T> withError(String error) {
        this.error = error;
        return this;
    }

    public OpenFormatFormatted<T> getFormatted() {
        return formatted;
    }

    public void setFormatted(OpenFormatFormatted<T> formatted) {
        this.formatted = formatted;
    }

    public OpenFormatDisplay<T> withFormatted(OpenFormatFormatted<T> formatted) {
        this.formatted = formatted;
        return this;
    }

    @Override
    public String toString() {
        return "OpenFormatDisplay{" +
                "mediaType='" + mediaType + '\'' +
                ", error='" + error + '\'' +
                ", formatted=" + formatted +
                '}';
    }
}
