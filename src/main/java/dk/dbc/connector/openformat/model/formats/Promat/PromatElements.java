package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dk.dbc.connector.openformat.model.OpenFormatElements;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromatElements implements OpenFormatElements {

    public static class MaterialTypes {
        private List<String> type;

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }

        public MaterialTypes withType(List<String> type) {
            this.type = type;
            return this;
        }
    }

    public static class CatalogCodes {
        private List<String> code;

        public List<String> getCode() {
            return code;
        }

        public void setCode(List<String> code) {
            this.code = code;
        }

        public CatalogCodes withCode(List<String> code) {
            this.code = code;
            return this;
        }
    }

    private List<String> faust = new ArrayList<>();
    private List<String> isbn = new ArrayList<>();
    private List<String> creator = new ArrayList<>();
    private List<String> dk5 = new ArrayList<>();
    private List<String> extent = new ArrayList<>();
    private List<String> publisher = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    @JsonProperty("targetgroup")
    private List<String> targetGroup = new ArrayList<>();
    @JsonProperty("metakompassubject")
    private List<String> metakompasSubject = new ArrayList<>();
    @JsonProperty("materialtypes")
    private MaterialTypes materialTypes;
    @JsonProperty("catalogcodes")
    private CatalogCodes catalogCodes;

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

    public List<String> getCreator() {
        return creator;
    }

    public void setCreator(List<String> creator) {
        this.creator = creator;
    }

    public PromatElements withCreator(List<String> creator) {
        this.creator = creator;
        return this;
    }

    public List<String> getDk5() {
        return dk5;
    }

    public void setDk5(List<String> dk5) {
        this.dk5 = dk5;
    }

    public PromatElements withDk5(List<String> dk5) {
        this.dk5 = dk5;
        return this;
    }

    public List<String> getExtent() {
        return extent;
    }

    public void setExtent(List<String> extent) {
        this.extent = extent;
    }

    public PromatElements withExtent(List<String> extent) {
        this.extent = extent;
        return this;
    }

    public List<String> getPublisher() {
        return publisher;
    }

    public void setPublisher(List<String> publisher) {
        this.publisher = publisher;
    }

    public PromatElements withPublisher(List<String> publisher) {
        this.publisher = publisher;
        return this;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public PromatElements withTitle(List<String> title) {
        this.title = title;
        return this;
    }

    public List<String> getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(List<String> targetGroup) {
        this.targetGroup = targetGroup;
    }

    public PromatElements withTargetgroup(List<String> targetgroup) {
        this.targetGroup = targetgroup;
        return this;
    }

    public List<String> getMetakompasSubject() {
        return metakompasSubject;
    }

    public void setMetakompasSubject(List<String> metakompasSubject) {
        this.metakompasSubject = metakompasSubject;
    }

    public PromatElements withMetakompassubject(List<String> metakompassubject) {
        this.metakompasSubject = metakompassubject;
        return this;
    }

    public MaterialTypes getMaterialTypes() {
        return materialTypes;
    }

    public void setMaterialTypes(MaterialTypes materialTypes) {
        this.materialTypes = materialTypes;
    }

    public PromatElements withMaterialTypes(MaterialTypes materialTypes) {
        this.materialTypes = materialTypes;
        return this;
    }

    public CatalogCodes getCatalogCodes() {
        return catalogCodes;
    }

    public void setCatalogCodes(CatalogCodes catalogCodes) {
        this.catalogCodes = catalogCodes;
    }

    public PromatElements withCatalogCodes(CatalogCodes catalogCodes) {
        this.catalogCodes = catalogCodes;
        return this;
    }

    @Override
    public String toString() {
        return "PromatElements{" +
                "faust=" + faust +
                ", isbn=" + isbn +
                ", creator=" + creator +
                ", dk5=" + dk5 +
                ", extent=" + extent +
                ", publisher=" + publisher +
                ", title=" + title +
                ", targetGroup=" + targetGroup +
                ", metakompasSubject=" + metakompasSubject +
                ", materialTypes=" + materialTypes +
                ", catalogCodes=" + catalogCodes +
                '}';
    }

    @Override
    public String getName() {
        return "promat";
    }
}
