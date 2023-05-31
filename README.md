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

## Running Tests
After the app has been started on `localhost:8080`, you can run tests. 

One of the easiest ways to run tests, recommended for non-programmers or teams that are not familiar with Java, is to use [Visual Studio Code](https://github.com/karatelabs/karate/wiki/Get-Started:-Visual-Studio-Code).

There are more tests and examples in this project, but the following are the simplest ones to get started with:

### API Test
* [api/simple.feature](src/test/java/app/api/simple/simple.feature)

### UI Test
* [ui/simple.feature](src/test/java/app/ui/simple/simple.feature)

### API Mock
* [mock/test.feature](src/test/java/app/mock/test.feature) - this would run the API test, but after starting the mock defined in [mock.feature](src/test/java/app/mock/mock.feature)

### API Performance Test
To run performance test (after the app has been started on `localhost`):

```
mvn test -P gatling
```

The above command uses Maven and has to be run on the command-line.