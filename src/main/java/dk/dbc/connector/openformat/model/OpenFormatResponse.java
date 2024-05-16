package dk.dbc.connector.openformat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OpenFormatResponse<T extends OpenFormatElements> {

    private List<OpenFormatObject<T>> objects;

    private String trackingId;

    public List<OpenFormatObject<T>> getObjects() {
        return objects;
    }

    public void setObjects(List<OpenFormatObject<T>> objects) {
        this.objects = objects;
    }

    public OpenFormatResponse<T> withObjects(List<OpenFormatObject<T>> objects) {
        this.objects = objects;
        return this;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public OpenFormatResponse<T> withTrackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    private List<T> traverseResponseForElements() {
        List<T> elements = new ArrayList<>();

        if (objects != null) {
            for(OpenFormatObject<T> obj : objects) {
                if (obj.getDisplays() != null) {
                    for(String name : obj.getDisplays().keySet()) {
                        List<OpenFormatDisplay<T>> displays = obj.getDisplays().get(name);
                        if (displays != null ) {
                            for (OpenFormatDisplay<T> display : displays) {
                                if (display.getFormatted() != null) {
                                    if (display.getFormatted().getRecords() != null) {
                                        for (OpenFormatRecord<T> record : display.getFormatted().getRecords()) {
                                            if (record.getElements() != null) {
                                                elements.add(record.getElements());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return elements;
    }

    /**
     * Helper method to dig down and get elements that contains a given faust number.
     * All formats must have a faust number represented in the elements object.
     * @param faust faust
     * @return list of elements if found, empty list otherwise
     */
    public List<T> getElementsWithFaust(String faust) {
        List<T> elements = traverseResponseForElements();
        return elements.stream()
                .filter(e -> e.getFaust().contains(faust))
                .collect(Collectors.toList());
    }

    /**
     * Helper method to dig down and get the first element that contains a given faust number.
     * All formats must have a faust number represented in the elements object.
     * @param faust faust
     * @return element if found, null otherwise
     */
    public T getElementWithFaust(String faust) {
        List<T> elements = getElementsWithFaust(faust);
        return elements.stream().findFirst().orElse(null);
    }

    /**
     * Helper method to dig down and get elements that contains a given isbn number
     * All formats must have an isbn number represented in the elements object.
     * @param isbn isbn
     * @return list of elements if found, empty list otherwise
     */
    public List<T> getElementsWithIsbn(String isbn) {
        List<T> elements = traverseResponseForElements();
        return elements.stream()
                .filter(e -> e.getIsbn().contains(isbn))
                .collect(Collectors.toList());
    }

    /**
     * Helper method to dig down and get the first element that contains a given isbn number
     * All formats must have an isbn number represented in the elements object.
     * @param isbn isbn
     * @return element if found, null otherwise
     */
    public T getElementWithIsbn(String isbn) {
        List<T> elements = getElementsWithIsbn(isbn);
        return elements.stream().findFirst().orElse(null);
    }

    /**
     * Helper method to dig down and get all errors
     * @return List of errors if found, empty list otherwise
     */
    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();
        if (objects != null) {
            for(OpenFormatObject<T> obj : objects) {
                if (obj.getDisplays() != null) {
                    for(String name : obj.getDisplays().keySet()) {
                        List<OpenFormatDisplay<T>> displays = obj.getDisplays().get(name);
                        if (displays != null ) {
                            for (OpenFormatDisplay<T> display : displays) {
                                if (display.getError() != null) {
                                    errors.add(display.getError());
                                }
                            }
                        }
                    }
                }
            }
        }
        return errors;
    }

    /**
     * Helper method to dig down and get the first error
     * @return error if found, empty list otherwise
     */
    public String getError() {
        List<String> errors = getErrors();
        return errors.stream().findFirst().orElse(null);
    }

    /**
     * Helper method to dig down and check if there are any errors
     * @return True if found, False otherwise
     */
    public Boolean hasError() {
        List<String> errors = getErrors();
        return !errors.isEmpty();
    }

    /**
     * Helper method to dig down and get the first element in the response.
     * This is usefull if you query for 1 faust and 1 format only, then
     * there is no need to go fishing for a specific faust- og isbnnumber to get
     * the element.
     * @return element if any was found, null otherwise
     */
    public T getElements() {
        List<T> elements = traverseResponseForElements();
        return elements.stream().findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "OpenFormatResponse{" +
                "objects=" + objects +
                ", trackingId='" + trackingId + '\'' +
                '}';
    }
}
