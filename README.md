# karate-todo

A self-contained demo project for [Karate](https://karatelabs.io) v2. Includes a sample app
(UI + API) and runnable examples of API tests, API mocks, UI automation, and performance tests.

Great for training, demos, or as a sandbox to learn Karate. No prior programming experience needed.

## IDE plugins

* [Karate for VS Code](https://marketplace.visualstudio.com/items?itemName=karatelabs.karate)
* [Karate for IntelliJ IDEA](https://plugins.jetbrains.com/plugin/19232-karate)

## Prerequisites

* [Java 21+](https://www.oracle.com/java/technologies/downloads) with `JAVA_HOME` set — required by [Karate v2](https://docs.karatelabs.io/getting-started/whats-new-v2)
* [Git](https://git-scm.com/download) (or [download as ZIP](https://github.com/karatelabs/karate-todo/archive/refs/heads/main.zip) and rename `karate-todo-main` → `karate-todo`)

> Using [GitHub Codespaces](https://github.com/karatelabs/karate/wiki/Get-Started:-GitHub-Codespaces) or a [VS Code devcontainer](https://code.visualstudio.com/docs/devcontainers/containers)? Java and Maven are pre-installed — skip to *Verify*.

## Get the code

```
git clone https://github.com/karatelabs/karate-todo.git
cd karate-todo
```

## Verify

```
./mvnw clean test
```

(Windows: `mvnw clean test`.) If `ApiTest` passes, you're good. All commands below assume the wrapper — drop the `./` and use plain `mvn` if you have [Maven installed](https://docs.karatelabs.io/getting-started/install-dependencies).

## Run the app

```
mvn test -Dtest=LocalRunner
```

Open http://localhost:8080 — a working TODO app backed by the same API the tests hit.
Stop with `Ctrl+C` or from your IDE.

## What's in the box

| Type | Entry point | Docs |
|---|---|---|
| API test | [`api/simple.feature`](src/test/java/app/api/simple/simple.feature) | [Making Requests](https://docs.karatelabs.io/http-requests/making-requests) |
| UI test | [`ui/simple.feature`](src/test/java/app/ui/simple/simple.feature) | [UI Testing](https://docs.karatelabs.io/extensions/ui-testing) |
| API mock | [`mock/test.feature`](src/test/java/app/mock/test.feature) (runs against [`mock.feature`](src/test/java/app/mock/mock.feature)) | [Test Doubles](https://docs.karatelabs.io/extensions/test-doubles) |
| Performance | [`perf/TodoSimulation.java`](src/test/java/app/perf/TodoSimulation.java) | [Performance Testing](https://docs.karatelabs.io/extensions/performance-testing) |

The same `simple.feature` runs against both the real API and the mock — a nice demonstration of Karate's contract-first testing.

## Performance test

Gatling hits the app over HTTP, so start it first. Two terminals:

```
# terminal 1 — app
mvn test -Dtest=LocalRunner

# terminal 2 — simulation
mvn test -P gatling
```

The HTML report is written to `target/gatling/todosimulation-*/index.html`.
Uses the [Java DSL for Gatling](https://github.com/karatelabs/karate/tree/develop/karate-gatling#java-dsl) (no Scala required).

## Gradle

See [Install & Get Started](https://docs.karatelabs.io/getting-started/install-dependencies) for the Gradle setup.
