package dk.dbc.connector.openformat.model;

import java.util.List;

public class OpenFormatFormatted<T extends OpenFormatElements> {
    private String format;

    private List<OpenFormatRecord<T>> records;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public OpenFormatFormatted<T> withFormat(String format) {
        this.format = format;
        return this;
    }

    public List<OpenFormatRecord<T>> getRecords() {
        return records;
    }

    public void setRecords(List<OpenFormatRecord<T>> records) {
        this.records = records;
    }

    public OpenFormatFormatted<T> withRecords(List<OpenFormatRecord<T>> records) {
        this.records = records;
        return this;
    }

    @Override
    public String toString() {
        return "OpenFormatFormatted{" +
                "format='" + format + '\'' +
                ", records=" + records +
                '}';
    }
}
