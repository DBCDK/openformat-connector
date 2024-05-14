package dk.dbc.connector.openformat.model.formats.Promat;

import dk.dbc.connector.openformat.model.OpenFormatResponse;

import java.util.List;

public class PromatResponse implements OpenFormatResponse {

    private List<PromatObject> objects;

    private String trackingId;

    public List<PromatObject> getObjects() {
        return objects;
    }

    public void setObjects(List<PromatObject> objects) {
        this.objects = objects;
    }

    public PromatResponse withObjects(List<PromatObject> objects) {
        this.objects = objects;
        return this;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public PromatResponse withTrackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    @Override
    public String getName() {
        return "promat";
    }

    /**
     * Helper method to dig down and get the element that contains a given faust number
     * @param faust faust
     * @return element if found, null otherwise
     */
    public PromatElements getElementsWithFaust(String faust) {
        if (objects != null) {
            for(PromatObject obj : objects) {
                if (obj.getDisplay() != null) {
                    for(PromatDisplay display : obj.getDisplay()) {
                        if (display.getFormatted() != null) {
                            if (display.getFormatted().getRecords() != null) {
                                for (PromatRecord record : display.getFormatted().getRecords()) {
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
     * @param isbn isbn
     * @return element if found, null otherwise
     */
    public PromatElements getElementsWithIsbn(String isbn) {
        if (objects != null) {
            for(PromatObject obj : objects) {
                if (obj.getDisplay() != null) {
                    for(PromatDisplay display : obj.getDisplay()) {
                        if (display.getFormatted() != null) {
                            if (display.getFormatted().getRecords() != null) {
                                for (PromatRecord record : display.getFormatted().getRecords()) {
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
