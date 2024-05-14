package dk.dbc.connector.openformat.model;

import java.util.List;

public class OpenFormatFormatted {
    private String format;

    private List<OpenFormatRecord> records;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public OpenFormatFormatted withFormat(String format) {
        this.format = format;
        return this;
    }

    public List<OpenFormatRecord> getRecords() {
        return records;
    }

    public void setRecords(List<OpenFormatRecord> records) {
        this.records = records;
    }

    public OpenFormatFormatted withRecords(List<OpenFormatRecord> records) {
        this.records = records;
        return this;
    }

    @Override
    public String toString() {
        return "PromatFormat{" +
                "format='" + format + '\'' +
                ", records=" + records +
                '}';
    }
}
