package dk.dbc.connector.openformat.model;

public class OpenFormatDisplay {
    private String mediaType;

    private String error;

    private OpenFormatFormatted formatted;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public OpenFormatDisplay withMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public OpenFormatDisplay withError(String error) {
        this.error = error;
        return this;
    }

    public OpenFormatFormatted getFormatted() {
        return formatted;
    }

    public void setFormatted(OpenFormatFormatted formatted) {
        this.formatted = formatted;
    }

    public OpenFormatDisplay withFormatted(OpenFormatFormatted formatted) {
        this.formatted = formatted;
        return this;
    }

    @Override
    public String toString() {
        return "PromatDisplay{" +
                "mediaType='" + mediaType + '\'' +
                ", error='" + error + '\'' +
                ", formatted=" + formatted +
                '}';
    }
}
