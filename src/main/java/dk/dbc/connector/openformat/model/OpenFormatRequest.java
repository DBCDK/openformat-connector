package dk.dbc.connector.openformat.model;

import java.util.ArrayList;
import java.util.List;

public class OpenFormatRequest {

    private List<OpenFormatRequestFormat> formats = new ArrayList<>();
    private List<OpenFormatRequestObject> objects = new ArrayList<>();

    public List<OpenFormatRequestFormat> getFormats() {
        return formats;
    }

    public List<OpenFormatRequestObject> getObjects() {
        return objects;
    }

    public void setFormats(List<OpenFormatRequestFormat> formats) {
        this.formats = formats;
    }

    public OpenFormatRequest withFormats(List<OpenFormatRequestFormat> formats) {
        this.formats = formats;
        return this;
    }

    public void setObjects(List<OpenFormatRequestObject> objects) {
        this.objects = objects;
    }

    public OpenFormatRequest withObjects(List<OpenFormatRequestObject> objects) {
        this.objects = objects;
        return this;
    }

    @Override
    public String toString() {
        return "OpenFormatRequest{" +
                "formats=" + formats +
                ", objects=" + objects +
                '}';
    }

    public OpenFormatRequest(String format, String repositoryId) {
        setFormats(List.of(new OpenFormatRequestFormat().withFormat(format)));
        setObjects(List.of(new OpenFormatRequestObject().withRepositoryId(repositoryId)));
    }

    public OpenFormatRequest(String format, String agency, String id) {
        setFormats(List.of(new OpenFormatRequestFormat().withFormat(format)));
        setObjects(List.of(new OpenFormatRequestObject().withRepositoryId(agency, id)));
    }
}
