openformat-connector
=============

A Java library providing an openformat client.

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
...

// Assumes environment variable OPENFORMAT_SERVICE_URL
// is set to the base URL of the openformat provider service.
@Inject
OpenFormatConnector connector;

// Todo: Add usage example


```

### development

**Requirements**

To build this project JDK 11 and Apache Maven is required.

### License

Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3.
See license text in LICENSE.txt
