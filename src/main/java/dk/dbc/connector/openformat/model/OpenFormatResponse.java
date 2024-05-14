package dk.dbc.connector.openformat.model;

import dk.dbc.connector.openformat.model.formats.Promat.PromatElements;

import java.util.List;

public class OpenFormatResponse<T extends OpenFormatElements> {

    private List<OpenFormatObject> objects;

    private String trackingId;

    public List<OpenFormatObject> getObjects() {
        return objects;
    }

    public void setObjects(List<OpenFormatObject> objects) {
        this.objects = objects;
    }

    public OpenFormatResponse withObjects(List<OpenFormatObject> objects) {
        this.objects = objects;
        return this;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public OpenFormatResponse withTrackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    /**
     * Helper method to dig down and get the element that contains a given faust number.
     * All formats must have a faust number represented in the elements object.
     * @param faust faust
     * @return element if found, null otherwise
     */
    public PromatElements getElementsWithFaust(String faust) {
        if (objects != null) {
            for(OpenFormatObject obj : objects) {
                if (obj.getDisplay() != null) {
                    for(OpenFormatDisplay display : obj.getDisplay()) {
                        if (display.getFormatted() != null) {
                            if (display.getFormatted().getRecords() != null) {
                                for (OpenFormatRecord record : display.getFormatted().getRecords()) {
                                    if (record.getElements() != null) {
                                        if (record.getElements().getFaust() != null && record.getElements().getFaust().contains(faust)) {
                                            return record.getElements();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Helper method to dig down and get the element that contains a given isbn number
     * All formats must have an isbn number represented in the elements object.
     * @param isbn isbn
     * @return element if found, null otherwise
     */
    public PromatElements getElementsWithIsbn(String isbn) {
        if (objects != null) {
            for(OpenFormatObject obj : objects) {
                if (obj.getDisplay() != null) {
                    for(OpenFormatDisplay display : obj.getDisplay()) {
                        if (display.getFormatted() != null) {
                            if (display.getFormatted().getRecords() != null) {
                                for (OpenFormatRecord record : display.getFormatted().getRecords()) {
                                    if (record.getElements() != null) {
                                        if (record.getElements().getIsbn() != null && record.getElements().getIsbn().contains(isbn)) {
                                            return record.getElements();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "PromatResponse{" +
                "objects=" + objects +
                ", trackingId='" + trackingId + '\'' +
                '}';
    }
}
