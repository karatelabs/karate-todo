# Overview

A 20 minute video intro to [Karate](https://www.karatelabs.io) that uses demos from this project can be viewed here: [API Testing With Karate](https://youtu.be/WT4gg7Jutzg).

# Instructions

## Prerequisites
* [Java JDK](https://www.oracle.com/java/technologies/downloads) - (at least version 11 or greater), [OpenJDK](https://openjdk.org/install) also works
* [`JAVA_HOME`](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux) environment variable set

## Verify Setup
If the following command runs the `ApiTest` fine, you are all set:

Windows:
```
mvnw clean test
```

Other:
```
./mvnw clean test
```

## Running `karate-todo`

> `mvn` will work if you have [Maven installed](https://maven.apache.org/install.html). Else replace it with `./mvnw` or `mvnw` like shown above.

Now you can run the `LocalRunner` class as  JUnit test. You can do this from an IDE that has Java support.

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