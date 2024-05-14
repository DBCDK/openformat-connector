package dk.dbc.connector.openformat.model.formats.Promat;

import java.util.List;

public class PromatFormatted {
    private String format;

    private List<PromatRecord> records;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public PromatFormatted withFormat(String format) {
        this.format = format;
        return this;
    }

    public List<PromatRecord> getRecords() {
        return records;
    }

    public void setRecords(List<PromatRecord> records) {
        this.records = records;
    }

    public PromatFormatted withRecords(List<PromatRecord> records) {
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
