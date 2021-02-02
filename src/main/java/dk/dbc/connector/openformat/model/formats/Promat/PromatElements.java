/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt or at https://opensource.dbc.dk/licenses/gpl-3.0/
 */

package dk.dbc.connector.openformat.model.formats.Promat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.dbc.connector.openformat.model.OpenFormatValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromatElements {

    private OpenFormatValue faust;
    private OpenFormatValue creator;
    private OpenFormatValue dk5;
    private OpenFormatValue isbn;
    private PromatMaterialTypes materialtypes;
    private OpenFormatValue extent;
    private OpenFormatValue publisher;
    private PromatCatalogCodes catalogcodes;
    private OpenFormatValue title;
    private OpenFormatValue targetgroup;

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

    public OpenFormatValue getCreator() {
        return creator;
    }

    public void setCreator(OpenFormatValue creator) {
        this.creator = creator;
    }

    public PromatElements withCreator(OpenFormatValue creator) {
        this.creator = creator;
        return this;
    }

    public OpenFormatValue getDk5() {
        return dk5;
    }

    public void setDk5(OpenFormatValue dk5) {
        this.dk5 = dk5;
    }

    public PromatElements withDk5(OpenFormatValue dk5) {
        this.dk5 = dk5;
        return this;
    }

    public OpenFormatValue getIsbn() {
        return isbn;
    }

    public void setIsbn(OpenFormatValue isbn) {
        this.isbn = isbn;
    }

    public PromatElements withIsbn(OpenFormatValue isbn) {
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

    public OpenFormatValue getPublisher() {
        return publisher;
    }

    public void setPublisher(OpenFormatValue publisher) {
        this.publisher = publisher;
    }

    public PromatElements withPublisher(OpenFormatValue publisher) {
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

    public OpenFormatValue getTargetgroup() {
        return targetgroup;
    }

    public void setTargetgroup(OpenFormatValue targetgroup) {
        this.targetgroup = targetgroup;
    }

    public PromatElements withTargetgroup(OpenFormatValue targetgroup) {
        this.targetgroup = targetgroup;
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
                '}';
    }
}
