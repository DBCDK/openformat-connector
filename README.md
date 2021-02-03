openformat-connector
=============

A Java library providing an openformat client.

### OpenFormat webservice

The openformat webservice has several usage modes which requires one to provide data for a
record to be formatted. It can also be called indirectly from OpenSearch, and finally, it 
supports a direct-call mode where one can provide a faust number and an agency, this
connector utilizes this method.

```curl
curl "http://openformat-php-master.frontend-prod.svc.cloud.dbc.dk/server.php?action=formatObject&pid=870970-basis:24699773&outputFormat=promat&outputType=json"
```


### Supported formats

The OpenFormat service provides several output formats - but only
one of those is currently implemented. Formats is defined under model.formats.*

"promat"
: Promat specific output format

### usage

Add the dependency to your Maven pom.xml

```xml
<dependency>
  <groupId>dk.dbc</groupId>
  <artifactId>openformat-connector</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
 In your Java code

```java
import dk.dbc.openformat.openformatConnector;
import javax.inject.Inject;

// ...

class BibliographicInfo {

    // Assumes environment variable OPENFORMAT_SERVICE_URL
    // is set to the base URL of the openformat provider service.
    @Inject
    OpenFormatConnector connector;

    // ...
    
    String getTitle(String faust) {
        try {
            PromatEntity entity = connector.format(faust, PromatEntity.class);
            PromatFormatResponse formatResponse = entity.getFormatResponse();
            if (formatResponse.getError().size() > 0 || formatResponse.getPromat().size() == 0) {
                return "(not found)";
            } else {
                return formatResponse.getPromat().get(0).getElements().getTitle();
            }
        } catch (OpenFormatConnectorException connectorException) {
            return String.format("Exception: %s", connectorException.getMessage());
        }
    }
}
```

### development

**Requirements**

To build this project JDK 11 and Apache Maven is required.

### License

Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3.
See license text in LICENSE.txt
