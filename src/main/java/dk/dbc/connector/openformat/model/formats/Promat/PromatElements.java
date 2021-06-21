/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dk.dbc.connector.openformat.model.OpenFormatDeserializer;
import dk.dbc.connector.openformat.model.OpenFormatValue;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromatElements {

    private OpenFormatValue faust = new OpenFormatValue();
    private List<OpenFormatValue> creator = new ArrayList<>();
    private List<OpenFormatValue> dk5 = new ArrayList<>();
    private List<OpenFormatValue> isbn = new ArrayList<>();
    private PromatMaterialTypes materialtypes = new PromatMaterialTypes();
    private OpenFormatValue extent = new OpenFormatValue();
    private List<OpenFormatValue> publisher = new ArrayList<>();
    private PromatCatalogCodes catalogcodes = new PromatCatalogCodes();
    private OpenFormatValue title = new OpenFormatValue();
    private List<OpenFormatValue> targetgroup = new ArrayList<>();
    private OpenFormatValue metakompassubject = new OpenFormatValue();

    public OpenFormatValue getFaust() {
        return faust;
    }

    public void setFaust(OpenFormatValue faust) {
        this.faust = faust;
    }

    public PromatElements withFaust(OpenFormatValue faust) {
        this.faust = faust;
        return this;
    }

    public List<OpenFormatValue> getCreator() {
        return creator;
    }

    @JsonDeserialize(using = OpenFormatDeserializer.class)
    public void setCreator(List<OpenFormatValue> creator) {
        this.creator = creator;
    }

    public PromatElements withCreator(List<OpenFormatValue> creator) {
        this.creator = creator;
        return this;
    }

    public List<OpenFormatValue> getDk5() {
        return dk5;
    }

    @JsonDeserialize(using = OpenFormatDeserializer.class)
    public void setDk5(List<OpenFormatValue> dk5) {
        this.dk5 = dk5;
    }

    public PromatElements withDk5(List<OpenFormatValue> dk5) {
        this.dk5 = dk5;
        return this;
    }

    public List<OpenFormatValue> getIsbn() {
        return isbn;
    }

    @JsonDeserialize(using = OpenFormatDeserializer.class)
    public void setIsbn(List<OpenFormatValue> isbn) {
        this.isbn = isbn;
    }

    public PromatElements withIsbn(List<OpenFormatValue> isbn) {
        this.isbn = isbn;
        return this;
    }

    public PromatMaterialTypes getMaterialtypes() {
        return materialtypes;
    }

    public void setMaterialtypes(PromatMaterialTypes materialtypes) {
        this.materialtypes = materialtypes;
    }

    public PromatElements withMaterialtypes(PromatMaterialTypes materialtypes) {
        this.materialtypes = materialtypes;
        return this;
    }

    public OpenFormatValue getExtent() {
        return extent;
    }

    public void setExtent(OpenFormatValue extent) {
        this.extent = extent;
    }

    public PromatElements withExtent(OpenFormatValue extent) {
        this.extent = extent;
        return this;
    }

    public List<OpenFormatValue> getPublisher() {
        return publisher;
    }

    @JsonDeserialize(using = OpenFormatDeserializer.class)
    public void setPublisher(List<OpenFormatValue> publisher) {
        this.publisher = publisher;
    }

    public PromatElements withPublisher(List<OpenFormatValue> publisher) {
        this.publisher = publisher;
        return this;
    }

    public PromatCatalogCodes getCatalogcodes() {
        return catalogcodes;
    }

    public void setCatalogcodes(PromatCatalogCodes catalogcodes) {
        this.catalogcodes = catalogcodes;
    }

    public PromatElements withCatalogcodes(PromatCatalogCodes catalogcodes) {
        this.catalogcodes = catalogcodes;
        return this;
    }

    public OpenFormatValue getTitle() {
        return title;
    }

    public void setTitle(OpenFormatValue title) {
        this.title = title;
    }

    public PromatElements withTitle(OpenFormatValue title) {
        this.title = title;
        return this;
    }

    public List<OpenFormatValue> getTargetgroup() {
        return targetgroup;
    }

    @JsonDeserialize(using = OpenFormatDeserializer.class)
    public void setTargetgroup(List<OpenFormatValue> targetgroup) {
        this.targetgroup = targetgroup;
    }

    public PromatElements withTargetgroup(List<OpenFormatValue> targetgroup) {
        this.targetgroup = targetgroup;
        return this;
    }

    public OpenFormatValue getMetakompassubject() {
        return metakompassubject;
    }

    public void setMetakompassubject(OpenFormatValue metakompassubject) {
        this.metakompassubject = metakompassubject;
    }

    public PromatElements withMetakompassubject(OpenFormatValue metakompassubject) {
        this.metakompassubject = metakompassubject;
        return this;
    }

    @Override
    public String toString() {
        return "PromatElements{" +
                "faust=" + faust +
                ", creator=" + creator +
                ", dk5=" + dk5 +
                ", isbn=" + isbn +
                ", materialtypes=" + materialtypes +
                ", extent=" + extent +
                ", publisher=" + publisher +
                ", catalogcodes=" + catalogcodes +
                ", title=" + title +
                ", targetgroup=" + targetgroup +
                ", metakompassubject=" + metakompassubject +
                '}';
    }
}
