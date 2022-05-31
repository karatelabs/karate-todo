Feature:

  Background:
    * configure driver = { type: 'chrome' }
    * driver 'http://localhost:8080'

  Scenario:
    * if (locateAll('.border-bottom').length == 0) karate.abort()
    * click('.form-switch input')
    * click('{}Clear Completed')
    * waitUntil(() => locateAll('.border-bottom').length == 0)
