# TODO(karate-js): re-enable once karate-js 2.0.5 fixes the JS regressions
# surfaced by the called `waitUntil(find)` loop.
@todo @ui @data-driven
Feature: data-driven UI flow via a called feature

  Scenario:
    * driver serverUrl
    * call read('called.feature')

