# Instructions

## Prerequisites
* Git
* Java JDK
* Maven

## `karate-core`
For now, we depend on the `develop` version of `karate-core`, which has to be built locally and the version is `2.0.0`. 

```
git clone https://github.com/karatelabs/karate.git
cd karate
git checkout develop
mvn install -f karate-core/pom.xml
```

Now you can run the `LocalRunner` class as  JUnit test. You can do this from the IDE.

Or from the command-line:

```
mvn test -Dtest=LocalRunner
```

# Architecture
An earlier version of this application and the explanation can be found here: https://github.com/ptrthomas/karate-todomvc

The version here is using Bootstrap for the theme. Some more work is needed for the UI.