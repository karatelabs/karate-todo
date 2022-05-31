Feature:

  Background:
    * configure driver = { type: 'chrome' }
    * driver 'http://localhost:8080'

  Scenario:
    * input('input[name=title]', 'Task One' + Key.ENTER)
    * waitFor('.border-bottom div').mouse().doubleClick()
    * waitFor('.border-bottom input').clear().input('Task Edit')
    * mouse('h1').click()
    * waitForText('.border-bottom', 'Task Edit')
