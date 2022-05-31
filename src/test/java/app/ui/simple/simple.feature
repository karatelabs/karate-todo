Feature:

  Background:
    * configure driver = { type: 'chrome' }
    * driver 'http://localhost:8080'

  Scenario:
    * input('input[name=title]', 'Task One' + Key.ENTER)
    * waitForText('.border-bottom', 'Task One')
    * waitFor('button').click()
    * waitUntil(() => locateAll('.border-bottom').length == 0)
