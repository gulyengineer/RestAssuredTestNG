# REST Assured TestNG API Tests

API testing framework built with REST Assured and TestNG. It targets a running API and executes the suite defined in `suite.xml`.

## Requirements
- Java 17
- Maven 3.x
- Network access to the API base URL

## Configuration

### Environment variables
The tests require credentials. Set these in your shell or a local `.env` file:

```
TEST_USERNAME=your_username
TEST_PASSWORD=your_password
TEST_EMAIL=your_email@example.com
BASE_URI=http://your_base_uri:8080/
```

### Logging
Logs go to console and `logs/test.log` (Log4j2 config in `src/test/resources/log4j2.xml`).

## Running tests
The Maven Surefire plugin uses the suite name from the `suite` property.

Run the default suite:

```
mvn clean test -Dsuite=suite
```

Run a different suite:
1. Create `my-suite.xml` at the repo root.
2. Execute:

```
mvn clean test -Dsuite=my-suite
```

Test reports are generated under `target/surefire-reports`.

## Project layout
- `src/test/java/com/example/tests`: TestNG test classes
- `src/test/java/com/example/base`: REST Assured service wrappers
- `src/test/java/com/example/utils`: utilities and shared test data
- `src/test/resources`: test data and logging config
