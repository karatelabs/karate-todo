# Overview

This is a self-contained project that is great for training or demo-ing all capabilities of [Karate](https://karatelabs.io). It includes an app that has a working front-end UI and back-end API. Examples of API tests, API performance tests, API mocks and Web-Browser automation are included.

An 8 minute video ideal for beginners can be found here. [Karate Kick Start - The TODO Sample and Demo Project](https://youtu.be/gDZWgV3OubY). No programming or automation experience is required.

A longer video (20 minutes) which is a good introduction to Karate uses demos in this project and can be an additional reference: [API Testing with Karate](https://youtu.be/WT4gg7Jutzg).

Use the official Karate IDE plugins for the best developer experience:

* [Karate extension for Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=karatelabs.karate)
* [Karate plugin for IntelliJ IDEA](https://plugins.jetbrains.com/plugin/19232-karate)

# Instructions

> You can use [GitHub Codespaces](https://github.com/karatelabs/karate/wiki/Get-Started:-GitHub-Codespaces) to open this project directly in your browswer ! The default image includes Java and Maven, so you can skip the "Prerequisites" section below and go directly to [Verify Setup](#verify-setup). Make sure you install the [Karate extension for Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=karatelabs.karate) in your Codespace. The Karate extension for VS Code can also be run in a Docker based [devcontainer](https://code.visualstudio.com/docs/devcontainers/containers).

## Prerequisites
* [Git](https://git-scm.com/download) - to clone this project, or you could just [download the source code as a ZIP file](https://github.com/karatelabs/karate-todo/archive/refs/heads/main.zip)
* [Java JDK](https://www.oracle.com/java/technologies/downloads) - (at least version 11 or greater), [OpenJDK](https://jdk.java.net/) also works
* [`JAVA_HOME`](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux) environment variable set

## Get Source Code
* open a terminal in a folder in which `karate-todo` will be created
* enter the following command: `git clone https://github.com/karatelabs/karate-todo.git`

If you don't have Git installed, you can [download the source code as a ZIP file](https://github.com/karatelabs/karate-todo/archive/refs/heads/main.zip) and extract it. The folder you get may be called `karate-todo-main`, so just re-name it to `karate-todo`.

## Verify Setup
Open a terminal in the directory called `karate-todo`.

If the following command runs the `ApiTest` fine, you are all set:

| Windows | Linux / Mac |
| ------- | ----------- |
| `mvnw clean test` | `./mvnw clean test` |

## Running `karate-todo`

> In the commands below, `mvn` will work if you have [Maven installed](https://github.com/karatelabs/karate/wiki/Get-Started:-Maven-and-Gradle). Else replace it with `./mvnw` or `mvnw` like shown above to use the [Maven wrapper](https://maven.apache.org/wrapper).

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

Documentation: [Karate API Testing](https://karatelabs.github.io/karate)

### UI Test
* [ui/simple.feature](src/test/java/app/ui/simple/simple.feature)

Documentation: [Karate UI Testing](https://karatelabs.github.io/karate/karate-core)

### API Mock
* [mock/test.feature](src/test/java/app/mock/test.feature) - this would run the API test after starting the mock defined in [mock.feature](src/test/java/app/mock/mock.feature). Note how the same test ([simple.feature](src/test/java/app/api/simple/simple.feature)) works for both the "real" API and the mock.

Documentation: [Karate API Mocks](https://karatelabs.github.io/karate/karate-netty)

### API Performance Test
To run performance test (after the app has been started on `localhost`):

```
mvn test -P gatling
```

The above command uses Maven and has to be run on the command-line. The entry point is [perf/TodoSimulation.scala](src/test/java/app/perf/TodoSimulation.scala). The Maven [pom.xml](pom.xml) has a `<profile>` called `gatling`, which sets up the performance test and the `karate-gatling` dependency.

Documentation: [Karate API Performance Testing](https://karatelabs.github.io/karate/karate-gatling)