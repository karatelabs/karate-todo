# TODO(karate-js): re-enable once karate-js 2.0.5 parses `() => ...`
# zero-param arrow functions correctly.
@todo
Feature:

  Background:
    * driver serverUrl

  Scenario:
    * if (locateAll('.border-bottom').length == 0) karate.abort()
    * click('.form-switch input')
    * click('{}Clear Completed')
    * waitUntil(() => locateAll('.border-bottom').length == 0)
