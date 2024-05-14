package dk.dbc.connector.openformat.model;

public class OpenFormatRequestObject {
    private String repositoryId;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    OpenFormatRequestObject setRepositoryId(String agency, String id) {
        this.repositoryId = agency + "-basis:" + id;
        return this;
    }

    OpenFormatRequestObject withRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    OpenFormatRequestObject withRepositoryId(String agency, String id) {
        this.repositoryId = agency + "-basis:" + id;
        return this;
    }

    @Override
    public String toString() {
        return "OpenFormatRequestObject{" +
                "repositoryId='" + repositoryId + '\'' +
                '}';
    }
}
