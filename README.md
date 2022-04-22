# Instructions

## Prerequisites
* Java JDK
* Maven

## Verify Setup
If the following command runs the `ApiTest` fine, you are all set:

```
mvn clean test
```

## Running `karate-todo`

Now you can run the `LocalRunner` class as  JUnit test. You can do this from the IDE.

Or from the command-line:

```
mvn clean test -Dtest=LocalRunner
```

Now you should see the front-end at http://localhost:8080

To stop, just kill the process or stop the Java process from the IDE.

# Karate Gatling
To run performance test (after the app has been started on `localhost`):

```
mvn test -P gatling
```