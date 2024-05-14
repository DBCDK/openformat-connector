package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.dbc.connector.openformat.model.OpenFormatElements;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromatElements implements OpenFormatElements {
    private List<String> faust;
    private List<String> isbn;


    /*
      Todo: Add elements...
        private List<OpenFormatValue> creator = new ArrayList<>();
        private List<OpenFormatValue> dk5 = new ArrayList<>();
        private PromatMaterialTypes materialtypes = new PromatMaterialTypes();
        private OpenFormatValue extent = new OpenFormatValue();
        private List<OpenFormatValue> publisher = new ArrayList<>();
        private PromatCatalogCodes catalogcodes = new PromatCatalogCodes();
        private OpenFormatValue title = new OpenFormatValue();
        private List<OpenFormatValue> targetgroup = new ArrayList<>();
        private OpenFormatValue metakompassubject = new OpenFormatValue();
     */

    public List<String> getFaust() {
        return faust;
    }

    public void setFaust(List<String> faust) {
        this.faust = faust;
    }

    public PromatElements withFaust(List<String> faust) {
        this.faust = faust;
        return this;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public PromatElements withIsbn(List<String> isbn) {
        this.isbn = isbn;
        return this;
    }

    @Override
    public String toString() {
        return "PromatElements{" +
                "faust=" + faust +
                ", isbn=" + isbn +
                '}';
    }

    @Override
    public String getName() {
        return "promat";
    }
}
