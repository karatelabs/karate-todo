# karate-todo

[![cicd](https://github.com/karatelabs/karate-todo/actions/workflows/cicd.yml/badge.svg)](https://github.com/karatelabs/karate-todo/actions/workflows/cicd.yml)

The flagship demo for [Karate](https://karatelabs.io) **v2** — a self-contained TODO app wired up
with runnable examples of API tests, API mocks, UI automation (Testcontainers + headless Chrome),
performance tests (Gatling), and a full GitHub Actions CI pipeline that publishes HTML reports
to [karatelabs.github.io/karate-todo](https://karatelabs.github.io/karate-todo/latest/).

Great for trainings, demos, or as a sandbox to learn Karate. No prior programming experience needed.

## See it live

These are produced fresh on every green push to `main`:

| Report | Link |
|---|---|
| Karate summary (API + UI) | [karate-summary.html](https://karatelabs.github.io/karate-todo/latest/karate/karate-summary.html) |
| Parallel timeline | [karate-timeline.html](https://karatelabs.github.io/karate-todo/latest/karate/karate-timeline.html) |
| UI feature with embedded screenshots | [simple.feature](https://karatelabs.github.io/karate-todo/latest/karate/feature-html/target.test-classes.app.ui.simple.simple.html) |
| API CRUD feature (request / response log) | [simple.feature](https://karatelabs.github.io/karate-todo/latest/karate/feature-html/target.test-classes.app.api.simple.simple.html) |
| Gatling performance report | [index.html](https://karatelabs.github.io/karate-todo/latest/gatling/index.html) |

In the Karate summary, try the **tag filter** (top right) to narrow down to `@smoke`, `@crud`, `@api`, `@ui`, `@data-driven`,
`@java-interop`, `@match`, or `@call`.

## Quickstart

```
git clone https://github.com/karatelabs/karate-todo.git
cd karate-todo
./mvnw test
```

If `ApiTest` passes, you're good — the full API suite (call, data, match, interop, simple) ran against
an in-process app. No Docker needed.

## Run the app

```
./mvnw test -Dtest=LocalRunner
```

Open [http://localhost:8080](http://localhost:8080) — a working TODO app backed by the same API the tests hit.
Stop with `Ctrl+C`.

The app uses a **singleton session** so tests mutate the same state you see in the browser. Run
`LocalRunner` in one terminal and `ApiTest` in another to watch API-driven mutations land in the UI.

## What's in the box

| Type | Entry point | Docs |
|---|---|---|
| API tests | [`app/api/`](src/test/java/app/api/) — run by [`ApiTest.java`](src/test/java/app/api/ApiTest.java) | [Making Requests](https://docs.karatelabs.io/http-requests/making-requests) |
| UI tests (Testcontainers) | [`app/ui/`](src/test/java/app/ui/) — run by [`UiTest.java`](src/test/java/app/ui/UiTest.java) with `-Pui` | [UI Testing](https://docs.karatelabs.io/extensions/ui-testing) |
| API mock | [`app/mock/`](src/test/java/app/mock/) — stand up locally via [`MockRunner.java`](src/test/java/app/mock/MockRunner.java) | [Test Doubles](https://docs.karatelabs.io/extensions/test-doubles) |
| Performance | [`app/perf/TodoSimulation.java`](src/test/java/app/perf/TodoSimulation.java) | [Performance Testing](https://docs.karatelabs.io/extensions/performance-testing) |

Feature files tagged `@external` hit real external hosts (httpbin, jsonplaceholder, restful-booker, google) —
excluded from CI by `~@external`.

## UI tests with Testcontainers

UI tests run a containerized `chromedp/headless-shell` via [`ContainerDriverProvider`](src/test/java/app/ui/support/ContainerDriverProvider.java),
with the in-process app reachable from the browser at `host.docker.internal`:

```
./mvnw verify -Pui
```

Needs Docker running. The profile is opt-in so `./mvnw test` stays fast and Docker-free for training setups.

## Performance (Gatling)

Two terminals:

```
# terminal 1 — app
./mvnw test -Dtest=LocalRunner

# terminal 2 — simulation
./mvnw test -P gatling
```

HTML report at `target/gatling/todosimulation-*/index.html`. Uses the
[Java DSL for Gatling](https://github.com/karatelabs/karate/tree/develop/karate-gatling#java-dsl) — no Scala required.

## GitHub Codespaces

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://codespaces.new/karatelabs/karate-todo)

The devcontainer ships with JDK 21, Maven, and Docker-in-Docker so every command above works on a fresh codespace.
Port 8080 auto-forwards for `LocalRunner`. After `./mvnw verify -Pui`, right-click any report
`.html` and **Open with Live Server** — port 5500 is forwarded so the rendered report opens in a browser tab.

## CI/CD

[`.github/workflows/cicd.yml`](.github/workflows/cicd.yml) stages jobs as `tests` → `gatling` → `secret-scan` → `publish`,
runs on every push to `main` and on manual dispatch, and publishes the assembled reports to GitHub Pages.

| Stage | What it does |
|---|---|
| `tests` | `./mvnw verify -Pui` — one hybrid suite (API + UI) via Testcontainers + headless Chrome |
| `gatling` | Starts `LocalRunner` in the background, runs `TodoSimulation` against it |
| `secret-scan` | Greps the report artifacts for common token / private-key patterns — fails the build on hit |
| `publish` | On `main` only: assembles `latest/{karate,gatling}/` and pushes to `gh-pages` |

Live reports: [karatelabs.github.io/karate-todo/latest/](https://karatelabs.github.io/karate-todo/latest/)

## IDE plugins

* [Karate for VS Code](https://marketplace.visualstudio.com/items?itemName=karatelabs.karate)
* [Karate for IntelliJ IDEA](https://plugins.jetbrains.com/plugin/19232-karate)

## Prerequisites

* [Java 21+](https://www.oracle.com/java/technologies/downloads) with `JAVA_HOME` set — required by [Karate v2](https://docs.karatelabs.io/getting-started/whats-new-v2)
* [Git](https://git-scm.com/download) (or [download as ZIP](https://github.com/karatelabs/karate-todo/archive/refs/heads/main.zip))
* [Docker](https://docs.docker.com/get-docker/) — only needed for `./mvnw verify -Pui`

> Codespaces users and VS Code devcontainer users: everything above is preinstalled — skip straight to *Quickstart*.

## Gradle

See [Install & Get Started](https://docs.karatelabs.io/getting-started/install-dependencies) for Gradle setup.
