package dk.dbc.connector.openformat.model.formats.Promat;

public class PromatDisplay {
    private String mediaType;

    private String error;

    private PromatFormatted formatted;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public PromatDisplay withMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public PromatDisplay withError(String error) {
        this.error = error;
        return this;
    }

    public PromatFormatted getFormatted() {
        return formatted;
    }

    public void setFormatted(PromatFormatted formatted) {
        this.formatted = formatted;
    }

    public PromatDisplay withFormatted(PromatFormatted formatted) {
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
