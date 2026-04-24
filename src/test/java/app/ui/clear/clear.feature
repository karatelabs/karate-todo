@ignore @ui @cleanup
Feature: clear completed todos

  Background:
    * driver serverUrl

  Scenario:
    * if (locateAll('.border-bottom').length == 0) karate.abort()
    * click('.form-switch input')
    * click('{}Clear Completed')
    * waitUntil(() => locateAll('.border-bottom').length == 0)
